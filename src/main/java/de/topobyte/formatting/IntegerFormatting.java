/*
 * Copyright (c) 1994, 2013, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package de.topobyte.formatting;

/**
 * A utility class for converting primitive int and long values to hex, octal
 * and binary strings. Derived from Integer.java and Long.java from the OpenJDK
 * project.
 * 
 * @author Lee Boynton
 * @author Arthur van Hoff
 * @author Josh Bloch
 * @author Joseph D. Darcy
 * @author Sebastian Kuerten
 */
public class IntegerFormatting
{

	/**
	 * All possible chars for representing a number as a String (lowercase)
	 */
	final static char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
			'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
			'z' };

	/**
	 * All possible chars for representing a number as a String (uppercase)
	 */
	final static char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
			'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
			'Z' };

	private static char[] table(Case c)
	{
		return c == Case.Uppercase ? DIGITS : digits;
	}

	/**
	 * Returns a string representation of the integer argument as an unsigned
	 * integer in base&nbsp;16.
	 *
	 * <p>
	 * The unsigned integer value is the argument plus 2<sup>32</sup> if the
	 * argument is negative; otherwise, it is equal to the argument. This value
	 * is converted to a string of ASCII digits in hexadecimal (base&nbsp;16)
	 * with no extra leading {@code 0}s. If the unsigned magnitude is zero, it
	 * is represented by a single zero character {@code '0'}
	 * (<code>'&#92;u0030'</code>); otherwise, the first character of the
	 * representation of the unsigned magnitude will not be the zero character.
	 * The following characters are used as hexadecimal digits:
	 *
	 * <blockquote> {@code 0123456789abcdef} </blockquote>
	 *
	 * These are the characters <code>'&#92;u0030'</code> through
	 * <code>'&#92;u0039'</code> and <code>'&#92;u0061'</code> through
	 * <code>'&#92;u0066'</code>. If uppercase letters are desired, the
	 * {@link java.lang.String#toUpperCase()} method may be called on the
	 * result:
	 *
	 * <blockquote> {@code Integer.toHexString(n).toUpperCase()} </blockquote>
	 *
	 * @param i
	 *            an integer to be converted to a string.
	 * @return the string representation of the unsigned integer value
	 *         represented by the argument in hexadecimal (base&nbsp;16).
	 * @since JDK1.0.2
	 */
	public static String intToHexString(int i, Case c)
	{
		return intToUnsignedString(i, 4, table(c));
	}

	/**
	 * Returns a string representation of the integer argument as an unsigned
	 * integer in base&nbsp;8.
	 *
	 * <p>
	 * The unsigned integer value is the argument plus 2<sup>32</sup> if the
	 * argument is negative; otherwise, it is equal to the argument. This value
	 * is converted to a string of ASCII digits in octal (base&nbsp;8) with no
	 * extra leading {@code 0}s.
	 *
	 * <p>
	 * If the unsigned magnitude is zero, it is represented by a single zero
	 * character {@code '0'} (<code>'&#92;u0030'</code>); otherwise, the first
	 * character of the representation of the unsigned magnitude will not be the
	 * zero character. The following characters are used as octal digits:
	 *
	 * <blockquote> {@code 01234567} </blockquote>
	 *
	 * These are the characters <code>'&#92;u0030'</code> through
	 * <code>'&#92;u0037'</code>.
	 *
	 * @param i
	 *            an integer to be converted to a string.
	 * @return the string representation of the unsigned integer value
	 *         represented by the argument in octal (base&nbsp;8).
	 * @since JDK1.0.2
	 */
	public static String intToOctalString(int i)
	{
		return intToUnsignedString(i, 3, digits);
	}

	/**
	 * Returns a string representation of the integer argument as an unsigned
	 * integer in base&nbsp;2.
	 *
	 * <p>
	 * The unsigned integer value is the argument plus 2<sup>32</sup> if the
	 * argument is negative; otherwise it is equal to the argument. This value
	 * is converted to a string of ASCII digits in binary (base&nbsp;2) with no
	 * extra leading {@code 0}s. If the unsigned magnitude is zero, it is
	 * represented by a single zero character {@code '0'}
	 * (<code>'&#92;u0030'</code>); otherwise, the first character of the
	 * representation of the unsigned magnitude will not be the zero character.
	 * The characters {@code '0'} (<code>'&#92;u0030'</code>) and {@code '1'}
	 * (<code>'&#92;u0031'</code>) are used as binary digits.
	 *
	 * @param i
	 *            an integer to be converted to a string.
	 * @return the string representation of the unsigned integer value
	 *         represented by the argument in binary (base&nbsp;2).
	 * @since JDK1.0.2
	 */
	public static String intToBinaryString(int i)
	{
		return intToUnsignedString(i, 1, digits);
	}

	/**
	 * Returns a string representation of the {@code long} argument as an
	 * unsigned integer in base&nbsp;16.
	 *
	 * <p>
	 * The unsigned {@code long} value is the argument plus 2<sup>64</sup> if
	 * the argument is negative; otherwise, it is equal to the argument. This
	 * value is converted to a string of ASCII digits in hexadecimal
	 * (base&nbsp;16) with no extra leading {@code 0}s.
	 *
	 * <p>
	 * The value of the argument can be recovered from the returned string
	 * {@code s} by calling {@link Long#parseUnsignedLong(String, int)
	 * Long.parseUnsignedLong(s, 16)}.
	 *
	 * <p>
	 * If the unsigned magnitude is zero, it is represented by a single zero
	 * character {@code '0'} ({@code '\u005Cu0030'}); otherwise, the first
	 * character of the representation of the unsigned magnitude will not be the
	 * zero character. The following characters are used as hexadecimal digits:
	 *
	 * <blockquote> {@code 0123456789abcdef} </blockquote>
	 *
	 * These are the characters {@code '\u005Cu0030'} through
	 * {@code '\u005Cu0039'} and {@code '\u005Cu0061'} through
	 * {@code '\u005Cu0066'}. If uppercase letters are desired, the
	 * {@link java.lang.String#toUpperCase()} method may be called on the
	 * result:
	 *
	 * <blockquote> {@code Long.toHexString(n).toUpperCase()} </blockquote>
	 *
	 * @param i
	 *            a {@code long} to be converted to a string.
	 * @return the string representation of the unsigned {@code long} value
	 *         represented by the argument in hexadecimal (base&nbsp;16).
	 * @see #parseUnsignedLong(String, int)
	 * @see #longToUnsignedString(long, int)
	 * @since JDK 1.0.2
	 */
	public static String longToHexString(long i, Case c)
	{
		return longToUnsignedString(i, 4, table(c));
	}

	/**
	 * Returns a string representation of the {@code long} argument as an
	 * unsigned integer in base&nbsp;8.
	 *
	 * <p>
	 * The unsigned {@code long} value is the argument plus 2<sup>64</sup> if
	 * the argument is negative; otherwise, it is equal to the argument. This
	 * value is converted to a string of ASCII digits in octal (base&nbsp;8)
	 * with no extra leading {@code 0}s.
	 *
	 * <p>
	 * The value of the argument can be recovered from the returned string
	 * {@code s} by calling {@link Long#parseUnsignedLong(String, int)
	 * Long.parseUnsignedLong(s, 8)}.
	 *
	 * <p>
	 * If the unsigned magnitude is zero, it is represented by a single zero
	 * character {@code '0'} ({@code '\u005Cu0030'}); otherwise, the first
	 * character of the representation of the unsigned magnitude will not be the
	 * zero character. The following characters are used as octal digits:
	 *
	 * <blockquote> {@code 01234567} </blockquote>
	 *
	 * These are the characters {@code '\u005Cu0030'} through
	 * {@code '\u005Cu0037'}.
	 *
	 * @param i
	 *            a {@code long} to be converted to a string.
	 * @return the string representation of the unsigned {@code long} value
	 *         represented by the argument in octal (base&nbsp;8).
	 * @see #parseUnsignedLong(String, int)
	 * @see #longToUnsignedString(long, int)
	 * @since JDK 1.0.2
	 */
	public static String longToOctalString(long i)
	{
		return longToUnsignedString(i, 3, digits);
	}

	/**
	 * Returns a string representation of the {@code long} argument as an
	 * unsigned integer in base&nbsp;2.
	 *
	 * <p>
	 * The unsigned {@code long} value is the argument plus 2<sup>64</sup> if
	 * the argument is negative; otherwise, it is equal to the argument. This
	 * value is converted to a string of ASCII digits in binary (base&nbsp;2)
	 * with no extra leading {@code 0}s.
	 *
	 * <p>
	 * The value of the argument can be recovered from the returned string
	 * {@code s} by calling {@link Long#parseUnsignedLong(String, int)
	 * Long.parseUnsignedLong(s, 2)}.
	 *
	 * <p>
	 * If the unsigned magnitude is zero, it is represented by a single zero
	 * character {@code '0'} ({@code '\u005Cu0030'}); otherwise, the first
	 * character of the representation of the unsigned magnitude will not be the
	 * zero character. The characters {@code '0'} ({@code '\u005Cu0030'}) and
	 * {@code
	 * '1'} ({@code '\u005Cu0031'}) are used as binary digits.
	 *
	 * @param i
	 *            a {@code long} to be converted to a string.
	 * @return the string representation of the unsigned {@code long} value
	 *         represented by the argument in binary (base&nbsp;2).
	 * @see #parseUnsignedLong(String, int)
	 * @see #longToUnsignedString(long, int)
	 * @since JDK 1.0.2
	 */
	public static String longToBinaryString(long i, Case c)
	{
		return longToUnsignedString(i, 1, digits);
	}

	/**
	 * Convert the integer to an unsigned number.
	 */
	private static String intToUnsignedString(int i, int shift, char[] table)
	{
		char[] buf = new char[32];
		int charPos = 32;
		int radix = 1 << shift;
		int mask = radix - 1;
		do {
			buf[--charPos] = table[i & mask];
			i >>>= shift;
		} while (i != 0);

		return new String(buf, charPos, (32 - charPos));
	}

	/**
	 * Format a long (treated as unsigned) into a String.
	 * 
	 * @param val
	 *            the value to format
	 * @param shift
	 *            the log2 of the base to format in (4 for hex, 3 for octal, 1
	 *            for binary)
	 */
	private static String longToUnsignedString(long val, int shift,
			char[] table)
	{
		// assert shift > 0 && shift <=5 : "Illegal shift value";
		int mag = 64 /* Long.SIZE */ - numberOfLeadingZeros(val);
		int chars = Math.max(((mag + (shift - 1)) / shift), 1);
		char[] buf = new char[chars];

		formatUnsignedLong(val, shift, buf, 0, chars, table);
		return new String(buf);
	}

	/**
	 * Format a long (treated as unsigned) into a character buffer.
	 * 
	 * @param val
	 *            the unsigned long to format
	 * @param shift
	 *            the log2 of the base to format in (4 for hex, 3 for octal, 1
	 *            for binary)
	 * @param buf
	 *            the character buffer to write to
	 * @param offset
	 *            the offset in the destination buffer to start at
	 * @param len
	 *            the number of characters to write
	 * @return the lowest character location used
	 */
	private static int formatUnsignedLong(long val, int shift, char[] buf,
			int offset, int len, char[] table)
	{
		int charPos = len;
		int radix = 1 << shift;
		int mask = radix - 1;
		do {
			buf[offset + --charPos] = table[((int) val) & mask];
			val >>>= shift;
		} while (val != 0 && charPos > 0);

		return charPos;
	}

	/**
	 * Returns the number of zero bits preceding the highest-order ("leftmost")
	 * one-bit in the two's complement binary representation of the specified
	 * {@code long} value. Returns 64 if the specified value has no one-bits in
	 * its two's complement representation, in other words if it is equal to
	 * zero.
	 *
	 * <p>
	 * Note that this method is closely related to the logarithm base 2. For all
	 * positive {@code long} values x:
	 * <ul>
	 * <li>floor(log<sub>2</sub>(x)) = {@code 63 - numberOfLeadingZeros(x)}
	 * <li>ceil(log<sub>2</sub>(x)) = {@code 64 - numberOfLeadingZeros(x - 1)}
	 * </ul>
	 *
	 * @return the number of zero bits preceding the highest-order ("leftmost")
	 *         one-bit in the two's complement binary representation of the
	 *         specified {@code long} value, or 64 if the value is equal to
	 *         zero.
	 * @since 1.5
	 */
	private static int numberOfLeadingZeros(long i)
	{
		// HD, Figure 5-6
		if (i == 0) {
			return 64;
		}
		int n = 1;
		int x = (int) (i >>> 32);
		if (x == 0) {
			n += 32;
			x = (int) i;
		}
		if (x >>> 16 == 0) {
			n += 16;
			x <<= 16;
		}
		if (x >>> 24 == 0) {
			n += 8;
			x <<= 8;
		}
		if (x >>> 28 == 0) {
			n += 4;
			x <<= 4;
		}
		if (x >>> 30 == 0) {
			n += 2;
			x <<= 2;
		}
		n -= x >>> 31;
		return n;
	}

}
