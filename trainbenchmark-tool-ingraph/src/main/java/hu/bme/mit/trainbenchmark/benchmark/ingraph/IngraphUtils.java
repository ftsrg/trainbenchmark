package hu.bme.mit.trainbenchmark.benchmark.ingraph;

import java.lang.reflect.Method;

import relalg.RelalgContainer;

public class IngraphUtils {
    public static RelalgContainer getQueryPlan(final String name, final String variant) throws
            Exception {    	
        final String prefix = "ingraph.trainbenchmark.";
        final Class<?> factoryClass = Class.forName(prefix + name + "QueryPlanFactory");
        final Method declaredMethod = factoryClass.getDeclaredMethod(decapitalize(name) + variant);
        final Object factory = factoryClass.getConstructor().newInstance();
        return (RelalgContainer) declaredMethod.invoke(factory);
    }

    private static String decapitalize(final String string) {
        return Character.toLowerCase(string.charAt(0)) + string.substring(1);
    }

//    static Map<Object, Object> createIreTuple(Object pred, Object subj, Object obj) {
//        Map<Object, Object>.Map1()
//        return new Map<Object, Object>(1, 2, 3);
//    }
}
