/*
 * Copyright 2002-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.sunlights.common.utils;

import java.util.Collection;
import java.util.Map;


public abstract class Validate extends org.apache.commons.lang3.Validate {

    public static void isTrue(boolean expression, RuntimeException throwIfAssertFail) {
        if (!expression) {
            throw throwIfAssertFail;
        }
    }

    public static void isNull(Object object, RuntimeException throwIfAssertFail) {
        if (object != null) {
            throw throwIfAssertFail;
        }
    }

    public static void notNull(Object object, RuntimeException throwIfAssertFail) {
        if (object == null) {
            throw throwIfAssertFail;
        }
    }


    public static void notEmpty(Object[] array, RuntimeException throwIfAssertFail) {
        if (ObjectUtils.isEmpty(array)) {
            throw throwIfAssertFail;
        }
    }

    public static void noNullElements(Object[] array, RuntimeException throwIfAssertFail) {
        if (array != null) {
            for (Object element : array) {
                if (element == null) {
                    throw throwIfAssertFail;
                }
            }
        }
    }

    public static void notEmpty(Collection collection, RuntimeException throwIfAssertFail) {
        if (CollectionUtils.isEmpty(collection)) {
            throw throwIfAssertFail;
        }
    }

    public static void notEmpty(Map map, RuntimeException throwIfAssertFail) {
        if (CollectionUtils.isEmpty(map)) {
            throw throwIfAssertFail;
        }
    }

    /**
     * Assert that the given String is not empty; that is,
     * it must not be <code>null</code> and not the empty String.
     * <pre class="code">Assert.hasLength(name, "Name must not be empty");</pre>
     *
     * @param text    the String to check
     * @param message the exception message to use if the assertion fails
     * @see StringUtils#hasLength
     */
    public static void hasLength(String text, String message) {
        if (!StringUtils.hasLength(text)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Assert that the given String is not empty; that is,
     * it must not be <code>null</code> and not the empty String.
     * <pre class="code">Assert.hasLength(name);</pre>
     *
     * @param text the String to check
     * @see StringUtils#hasLength
     */
    public static void hasLength(String text) {
        hasLength(text,
                "this String argument must have length; it must not be null or empty");
    }


    public static void hasLength(String text, RuntimeException throwIfAssertFail) {
        if (!StringUtils.hasLength(text)) {
            throw throwIfAssertFail;
        }
    }

    /**
     * Assert that the given String has valid text content; that is, it must not
     * be <code>null</code> and must contain at least one non-whitespace character.
     * <pre class="code">Assert.hasText(name, "'name' must not be empty");</pre>
     *
     * @param text    the String to check
     * @param message the exception message to use if the assertion fails
     * @see StringUtils#hasText
     */
    public static void hasText(String text, String message) {
        if (!StringUtils.hasText(text)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Assert that the given String has valid text content; that is, it must not
     * be <code>null</code> and must contain at least one non-whitespace character.
     * <pre class="code">Assert.hasText(name, "'name' must not be empty");</pre>
     *
     * @param text the String to check
     * @see StringUtils#hasText
     */
    public static void hasText(String text) {
        hasText(text,
                "this String argument must have text; it must not be null, empty, or blank");
    }

    public static void hasText(String text, RuntimeException throwIfAssertFail) {
        if (!StringUtils.hasText(text)) {
            throw throwIfAssertFail;
        }
    }

    /**
     * Assert that the given text does not contain the given substring.
     * <pre class="code">Assert.doesNotContain(name, "rod", "Name must not contain 'rod'");</pre>
     *
     * @param textToSearch the text to search
     * @param substring    the substring to find within the text
     * @param message      the exception message to use if the assertion fails
     */
    public static void doesNotContain(String textToSearch, String substring, String message) {
        if (StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring) &&
                textToSearch.indexOf(substring) != -1) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Assert that the given text does not contain the given substring.
     * <pre class="code">Assert.doesNotContain(name, "rod");</pre>
     *
     * @param textToSearch the text to search
     * @param substring    the substring to find within the text
     */
    public static void doesNotContain(String textToSearch, String substring) {
        doesNotContain(textToSearch, substring,
                "this String argument must not contain the substring [" + substring + "]");
    }


    public static void doesNotContain(String textToSearch, String substring, RuntimeException throwIfAssertFail) {
        if (StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring)
                && textToSearch.indexOf(substring) != -1) {
            throw throwIfAssertFail;
        }
    }

    /**
     * Assert that the provided object is an instance of the provided class.
     * <pre class="code">Assert.instanceOf(Foo.class, foo);</pre>
     *
     * @param clazz the required class
     * @param obj   the object to check
     * @throws IllegalArgumentException if the object is not an instance of clazz
     * @see Class#isInstance
     */
    public static void isInstanceOf(Class clazz, Object obj) {
        isInstanceOf(clazz, obj, "");
    }

    /**
     * Assert that the provided object is an instance of the provided class.
     * <pre class="code">Assert.instanceOf(Foo.class, foo);</pre>
     *
     * @param type    the type to check against
     * @param obj     the object to check
     * @param message a message which will be prepended to the message produced by
     *                the function itself, and which may be used to provide context. It should
     *                normally end in a ": " or ". " so that the function generate message looks
     *                ok when prepended to it.
     * @throws IllegalArgumentException if the object is not an instance of clazz
     * @see Class#isInstance
     */
    public static void isInstanceOf(Class type, Object obj, String message) {
        notNull(type, "Type to check against must not be null");
        if (!type.isInstance(obj)) {
            throw new IllegalArgumentException(message +
                    "Object of class [" + (obj != null ? obj.getClass().getName() : "null") +
                    "] must be an instance of " + type);
        }
    }

    public static void isInstanceOf(Class type, Object obj, RuntimeException throwIfAssertFail) {
        notNull(type, "Type to check against must not be null");
        if (!type.isInstance(obj)) {
            throw throwIfAssertFail;
        }
    }

    /**
     * Assert that <code>superType.isAssignableFrom(subType)</code> is <code>true</code>.
     * <pre class="code">Assert.isAssignable(Number.class, myClass);</pre>
     *
     * @param superType the super type to check
     * @param subType   the sub type to check
     * @throws IllegalArgumentException if the classes are not assignable
     */
    public static void isAssignable(Class superType, Class subType) {
        isAssignable(superType, subType, "");
    }

    /**
     * Assert that <code>superType.isAssignableFrom(subType)</code> is <code>true</code>.
     * <pre class="code">Assert.isAssignable(Number.class, myClass);</pre>
     *
     * @param superType the super type to check against
     * @param subType   the sub type to check
     * @param message   a message which will be prepended to the message produced by
     *                  the function itself, and which may be used to provide context. It should
     *                  normally end in a ": " or ". " so that the function generate message looks
     *                  ok when prepended to it.
     * @throws IllegalArgumentException if the classes are not assignable
     */
    public static void isAssignable(Class superType, Class subType, String message) {
        notNull(superType, "Type to check against must not be null");
        if (subType == null || !superType.isAssignableFrom(subType)) {
            throw new IllegalArgumentException(message + subType + " is not assignable to " + superType);
        }
    }


    /**
     * Assert a boolean expression, throwing <code>IllegalStateException</code>
     * if the test result is <code>false</code>. Call isTrue if you wish to
     * throw IllegalArgumentException on an assertion failure.
     * <pre class="code">Assert.state(id == null, "The id property must not already be initialized");</pre>
     *
     * @param expression a boolean expression
     * @param message    the exception message to use if the assertion fails
     * @throws IllegalStateException if expression is <code>false</code>
     */
    public static void state(boolean expression, String message) {
        if (!expression) {
            throw new IllegalStateException(message);
        }
    }

    /**
     * Assert a boolean expression, throwing {@link IllegalStateException}
     * if the test result is <code>false</code>.
     * <p>Call {@link #isTrue(boolean)} if you wish to
     * throw {@link IllegalArgumentException} on an assertion failure.
     * <pre class="code">Assert.state(id == null);</pre>
     *
     * @param expression a boolean expression
     * @throws IllegalStateException if the supplied expression is <code>false</code>
     */
    public static void state(boolean expression) {
        state(expression, "this state invariant must be true");
    }

}
