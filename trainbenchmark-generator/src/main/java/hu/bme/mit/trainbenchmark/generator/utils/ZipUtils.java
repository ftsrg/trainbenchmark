package hu.bme.mit.trainbenchmark.generator.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Stream;

public class ZipUtils {
    public static <T,U> boolean zip(Iterable<T> ct, Stream<U> cu, ZipIterator<T,U> each) {
        Iterator<T> it = ct.iterator();
        Iterator<U> iu = cu.iterator();
        while (it.hasNext() && iu.hasNext()) {
            each.each(it.next(), iu.next());
        }
        return !it.hasNext() && !iu.hasNext();
    }
}
