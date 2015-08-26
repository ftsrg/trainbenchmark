#!/usr/bin/env python3

import json
import glob
import argparse


def parse(results, filename):
    with open(filename) as file:
        data = json.load(file)
        assoc_key = "JsonAssociationV1"
        schedule_key = "JsonScheduleV1"
        tiploc_key = "TiplocV1"
        seg_key = "schedule_segment"
        loc_key = "schedule_location"
        locations = list()

        for obj in data:
            # associations
            if assoc_key in obj.keys():
                uid = get_value(obj, assoc_key, "main_train_uid")
                assoc_uid = get_value(obj, assoc_key, "assoc_train_uid")

                if uid not in results[trains].keys():
                    new_train(results, uid)
                if not contain(results[trains][uid][assoc_label],
                               "AssociationUID", assoc_uid):
                    new_association(results, uid, assoc_uid,
                                    obj, assoc_key)

            # locations
            if tiploc_key in obj.keys():
                tiploc_code = get_value(obj, tiploc_key, "tiploc_code")
                stanox = get_value(obj, tiploc_key, "stanox")
                nalco = get_value(obj, tiploc_key, "nalco")

                new_location(results, tiploc_code)
                extend_location(results, tiploc_code, nalco, stanox)

            # schedules
            if schedule_key in obj.keys():
                uid = get_value(obj, schedule_key, "CIF_train_uid")

                if uid not in results[trains].keys():
                    new_train(results, uid)

                if seg_key in obj[schedule_key].keys():
                    if loc_key in obj[schedule_key][seg_key].keys():
                        locations.clear()
                        for loc in obj[schedule_key][seg_key][loc_key]:
                            locations.append(
                                {"Type": loc["location_type"],
                                 "TiplocCode": loc["tiploc_code"]
                                 })
                new_schedule(results, obj, uid, schedule_key, locations)


def attach_locations(results):
    locations = list()
    for uid in results[trains]:
        locations.clear()
        for sch in results[trains][uid][schedule_label]:
            prev = None
            for loc in sch[location_label]:
                if prev is not None:
                    new_location_neighbor(results, prev, loc["TiplocCode"])
                prev = loc["TiplocCode"]


def new_location(results, tiploc_code):
    if tiploc_code not in results[location_label].keys():
        results[location_label].update({tiploc_code: dict()})


def extend_location(results, tiploc_code, nalco, stanox):
    if "Code" not in results[location_label][tiploc_code].keys():
        results[location_label][tiploc_code]["Code"] = tiploc_code
        results[location_label][tiploc_code]["Stanox"] = stanox
        results[location_label][tiploc_code]["Nalco"] = nalco


def new_location_neighbor(results, prev_loc, current_loc):
    if neighbors not in results[location_label][prev_loc].keys():
        results[location_label][prev_loc][neighbors] = list()
    if current_loc not in results[location_label][prev_loc][neighbors]:
        results[location_label][prev_loc][neighbors].append(current_loc)


def new_train(results, uid):
    results[trains].update({uid: dict()})
    results[trains][uid].update({"UID": uid})
    results[trains][uid].update({assoc_label: list()})
    results[trains][uid].update({schedule_label: list()})


def new_schedule(results, obj, uid, schedule_key, locations):
    status = get_value(obj, schedule_key, "train_status")
    start_date = get_value(obj, schedule_key, "schedule_start_date")
    end_date = get_value(obj, schedule_key, "schedule_end_date")
    stp_indicator = get_value(obj, schedule_key, "CIF_stp_indicator")
    schedule_days_runs = get_value(obj, schedule_key, "schedule_days_runs")

    results[trains][uid][schedule_label].append({
        "Status": status,
        "StartDate": start_date,
        "EndDate": end_date,
        "STPIndicator": stp_indicator,
        "Days": schedule_days_runs,
        location_label: list()
    })
    for l in locations:
        # append locations to the last one schedule object
        results[trains][uid][schedule_label][-1][location_label].append(l)


def new_association(results, main_uid, assoc_uid, obj, assoc_key):
    category = get_value(obj, assoc_key, "category")
    location = get_value(obj, assoc_key, "location")
    stp_indicator = get_value(obj, assoc_key, "CIF_stp_indicator")
    assoc_days = get_value(obj, assoc_key, "assoc_days")
    start_date = get_value(obj, assoc_key, "assoc_start_date")
    end_date = get_value(obj, assoc_key, "assoc_end_date")

    results[trains][main_uid][assoc_label].append(
                        {"AssociationUID": assoc_uid,
                         "Category": category,
                         "Location": location,
                         "STPIndicator": stp_indicator,
                         "Days": assoc_days,
                         "StartDate": start_date,
                         "EndDate": end_date
                         })


def contain(dicts, key, value):
    """
    Search a value after its key parameter in a list of dictionaries provided
    by the dicts parameter. If the particular value is found in any of the
    dictionaries, the method returns True. In the other case, returns False.
    """
    for d in dicts:
        if key in d.keys():
            if d[key] == value:
                return True
    return False


def get_value(data, *keys):
    """
    Recursively iterate the nested keys and finally return the corresponding
    value from the data dictionary.

    Return None if the deepest key is not found in the data.
    """
    if isinstance(keys, tuple):
        key = keys[0]
        if key in data.keys():
            if len(keys) == 1:
                return data[key]
            if len(keys) == 2:
                return get_value(data[key], keys[1])
            else:
                return get_value(data[key], keys[1:-1])
        else:
            return None
    else:
        key = keys
        return data[key]


def describe(results):
    print("Number of trains: " + str(len(results[trains])))
    print("Number of locations: " + str(len(results[location_label])))
    n_assoc = 0
    n_visited_locs = 0
    n_undefined_locs = 0
    n_schedules = 0
    n_neighbors = 0
    neighbors_dict = dict()
    n_empty_status = 0
    n_empty_indicator = 0
    n_empty_start_date = 0
    n_empty_stanox = 0
    n_empty_nalco = 0

    for uid in results[trains]:
        n_assoc += len(results[trains][uid][assoc_label])
        # n_visited_locs += len(results[trains][uid][location_label])
        n_schedules += len(results[trains][uid][schedule_label])

        for sch in results[trains][uid][schedule_label]:
            prev_loc = None
            for loc in sch[location_label]:
                n_visited_locs += 1
                if loc["TiplocCode"] not in results[location_label].keys():
                    n_undefined_locs += 1
                if prev_loc is not None:
                    if prev_loc not in neighbors_dict.keys():
                        neighbors_dict.update({prev_loc: list()})
                    if loc["TiplocCode"] not in neighbors_dict[prev_loc]:
                        neighbors_dict[prev_loc].append(loc["TiplocCode"])
                prev_loc = loc["TiplocCode"]

            n_empty_status += increase_if_empty(sch, "Status")
            n_empty_indicator += increase_if_empty(sch, "STPIndicator")
            n_empty_start_date += increase_if_empty(sch, "StartDate")

    for loc in results[location_label]:
        if results[location_label][loc]["Stanox"] is None:
            n_empty_stanox += 1
        if results[location_label][loc]["Nalco"] is None:
            n_empty_nalco += 1
        if neighbors in results[location_label][loc].keys():
            n_neighbors += len(results[location_label][loc][neighbors])

    print("Number of associations: " + str(n_assoc))
    print("Number of visited locations: " + str(n_visited_locs))
    print("Number of schedules: " + str(n_schedules))
    print("Number of undefined locations: " + str(n_undefined_locs))
    print("Number of neighbors: " + str(n_neighbors))
    print("Number of empty status: " + str(n_empty_status))
    print("Number of empty indicators: " + str(n_empty_indicator))
    print("Number of empty start dates: " + str(n_empty_start_date))
    print("Number of empty stanox: " + str(n_empty_stanox))
    print("Number of empty nalco: " + str(n_empty_nalco))

    all_neighbors = 0
    for k in neighbors_dict.keys():
        all_neighbors += len(neighbors_dict[k])
    print(all_neighbors)


def increase_if_empty(sch, key):
    value = 0
    value += 1 if sch[key] == " " else 0
    value += 1 if sch[key] is None else 0
    return value


# important labels in the output file
trains = "Trains"
assoc_label = "Associations"
location_label = "Locations"
schedule_label = "Schedules"
neighbors = "Neighbors"

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("-d", "--describe",
                        help="just describe the model",
                        action="store_true")
    args = parser.parse_args()
    filenames = glob.glob("./schedules*.json")
    filenames.sort()
    results = dict()
    results.update({trains: dict()})
    results.update({location_label: dict()})

    if not args.describe:
        for f in filenames:
            print("Process: " + f)
            parse(results, f)
        print("Attach neighbors:")
        attach_locations(results)
        print("Done.")
        print("Writing the results: ")
        with open("./results.json", mode="w") as output:
            json.dump(obj=results, fp=output, indent=1, allow_nan=True)
        print("Done.")
    else:
        with open("./results.json") as file:
            results = json.load(file)
            describe(results)
