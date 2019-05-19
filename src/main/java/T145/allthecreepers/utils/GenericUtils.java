package T145.allthecreepers.utils;

import java.lang.reflect.Constructor;

public class GenericUtils {

	private GenericUtils() {}

	@SuppressWarnings("unchecked")
	public static <T> T construct(Class<T> targetClass, Object... constructorParams) {
		for (Constructor<?> constructor : targetClass.getConstructors()) {
			constructor.setAccessible(true);

			try {
				return (T) constructor.newInstance(constructorParams);
			} catch (ReflectiveOperationException err) {
				throw new RuntimeException(err);
			}
		}

		throw new IllegalArgumentException("None of the constructors matched the provided args!");
	}
}
