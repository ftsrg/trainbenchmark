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

        for obj in data:
            if assoc_key in obj.keys():
                uid = get_value(obj, assoc_key, "main_train_uid")
                assoc_uid = get_value(obj, assoc_key, "assoc_train_uid")
                category = get_value(obj, assoc_key, "category")
                location = get_value(obj, assoc_key, "location")
                stp_indicator = get_value(obj, assoc_key, "CIF_stp_indicator")

                if uid not in results[trains].keys():
                    new_train(results, uid, assoc_label, location_label)

                    new_association(results, uid, assoc_label, assoc_uid,
                                    category, location, stp_indicator)
                elif not contain(results[trains][uid][assoc_label],
                                 "AssociationUID", assoc_uid):
                    new_association(results, uid, assoc_label, assoc_uid,
                                    category, location, stp_indicator)

            if tiploc_key in obj.keys():
                tiploc_code = get_value(obj, tiploc_key, "tiploc_code")
                stanox = get_value(obj, tiploc_key, "stanox")
                nalco = get_value(obj, tiploc_key, "nalco")

                if tiploc_code not in results[location_label].keys():
                    new_location(results, tiploc_code, nalco, stanox)

            if schedule_key in obj.keys():
                uid = get_value(obj, schedule_key, "CIF_train_uid")
                status = get_value(obj, schedule_key, "train_status")
                start_date = get_value(obj, schedule_key, "schedule_start_date")
                transaction = get_value(obj, schedule_key, "transaction_type")
                stp_indicator = get_value(obj, schedule_key,
                                          "CIF_stp_indicator")
                if uid not in results[trains].keys():
                    new_train(results, uid, assoc_label, location_label)
                    extend_train(results, start_date, status, stp_indicator,
                                 transaction, uid)
                elif "Status" not in results[trains][uid].keys():
                    extend_train(results, start_date, status, stp_indicator,
                                 transaction, uid)

                if seg_key in obj[schedule_key].keys():
                    if loc_key in obj[schedule_key][seg_key].keys():
                        for loc in obj[schedule_key][seg_key][loc_key]:
                            if not contain(results[trains][uid][location_label],
                                           "TiplocCode", loc["tiploc_code"]
                                           ):
                                results[trains][uid][location_label].append(
                                              {"Type": loc["location_type"],
                                               "TiplocCode": loc["tiploc_code"]
                                               })


def new_location(results, tiploc_code, nalco, stanox):
    results[location_label].update({tiploc_code: dict()})
    results[location_label][tiploc_code].update({
        "Code": tiploc_code,
        "Stanox": stanox,
        "Nalco": nalco
    })


def extend_train(results, start_date, status, stp_indicator, transaction, uid):
    results[trains][uid].update({"Status": status,
                                 "StartDate": start_date,
                                 "Transaction": transaction,
                                 "STPIndicator": stp_indicator
                                 })


def new_association(results, main_uid, assoc_label, assoc_uid, category,
                    location, stp_indicator):
    association = results[trains][main_uid][assoc_label]
    association.append({"AssociationUID": assoc_uid,
                        "Category": category,
                        "Location": location,
                        "STPIndicator": stp_indicator
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


def new_train(results, uid, assoc_label, location_label):
    results[trains].update({uid: dict()})
    results[trains][uid].update({"UID": uid})
    results[trains][uid].update({assoc_label: list()})
    results[trains][uid].update({location_label: list()})


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
    n_empty_status = 0
    n_empty_indicator = 0
    n_empty_start_date = 0
    for uid in results[trains]:
        n_assoc += len(results[trains][uid][assoc_label])
        n_visited_locs += len(results[trains][uid][location_label])
        for loc in results[trains][uid][location_label]:
            if loc["TiplocCode"] not in results[location_label].keys():
                n_undefined_locs += 1
        n_empty_status += 1 if results[trains][uid]["Status"] == " " else 0
        n_empty_indicator += 1 if results[trains][uid]["STPIndicator"] == " " \
            else 0
        n_empty_start_date += 1 if results[trains][uid]["StartDate"] == " " \
            else 0
        n_empty_status += 1 if results[trains][uid]["Status"] is None else 0
        n_empty_indicator += 1 if results[trains][uid]["STPIndicator"] is None \
            else 0
        n_empty_start_date += 1 if results[trains][uid]["StartDate"] is None \
            else 0
    print("Number of associations: " + str(n_assoc))
    print("Number of visited locations: " + str(n_visited_locs))
    print("Number of undefined locations: " + str(n_undefined_locs))
    print("Number of empty status: " + str(n_empty_status))
    print("Number of empty indicators: " + str(n_empty_indicator))
    print("Number of empty start dates: " + str(n_empty_start_date))


trains = "Trains"
assoc_label = "Associations"
location_label = "Locations"

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("-d", "--describe",
                        help="just describe the model",
                        action="store_true")
    args = parser.parse_args()
    filenames = glob.glob("./schedules*.json")
    schedule = dict()
    schedule.update({trains: dict()})
    schedule.update({location_label: dict()})

    if not args.describe:
        for f in filenames:
            print(f)
            parse(schedule, f)

        print("Writing the results: ")
        with open("./results3.json", mode="w") as output:
            json.dump(obj=schedule, fp=output, indent=2, allow_nan=True)
        print("Done.")
    else:
        with open("./results3.json") as file:
            schedule = json.load(file)
            describe(schedule)
