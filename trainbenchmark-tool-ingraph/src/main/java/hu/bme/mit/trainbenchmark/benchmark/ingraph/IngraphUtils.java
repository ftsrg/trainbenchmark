package hu.bme.mit.trainbenchmark.benchmark.ingraph;

import ingraph.trainbenchmark.TrainBenchmarkUtil;
import relalg.RelalgContainer;
import scala.collection.immutable.Map;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class IngraphUtils {
    public static RelalgContainer getQueryPlan(String name, String variant) throws
            Exception {
        String prefix = "ingraph.trainbenchmark.";
        Class factoryClass = Class.forName(prefix + name + "QueryPlanFactory");
        Method declaredMethod = factoryClass.getDeclaredMethod(decapitalize(name) + variant);
        Object factory = factoryClass.getConstructor().newInstance();
        return (RelalgContainer) declaredMethod.invoke(factory);
    }

    private static String decapitalize(String string) {
        return Character.toLowerCase(string.charAt(0)) + string.substring(1);
    }

//    static Map<Object, Object> createIreTuple(Object pred, Object subj, Object obj) {
//        Map<Object, Object>.Map1()
//        return new Map<Object, Object>(1, 2, 3);
//    }
}
