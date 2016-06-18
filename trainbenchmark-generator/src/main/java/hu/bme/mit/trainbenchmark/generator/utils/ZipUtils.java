package hu.bme.mit.trainbenchmark.generator.utils;

import java.util.Iterator;
import java.util.stream.Stream;

public class ZipUtils {
    public static <T,U> boolean zip(final Iterable<T> ct, final Stream<U> cu, final ZipIterator<T,U> each) {
        final Iterator<T> it = ct.iterator();
        final Iterator<U> iu = cu.iterator();
        while (it.hasNext() && iu.hasNext()) {
            each.each(it.next(), iu.next());
        }
        return !it.hasNext() && !iu.hasNext();
    }
}
