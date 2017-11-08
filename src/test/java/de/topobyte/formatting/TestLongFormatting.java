// Copyright 2017 Sebastian Kuerten
//
// This file is part of integer-formatting.
//
// integer-formatting is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// integer-formatting is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with integer-formatting. If not, see <http://www.gnu.org/licenses/>.

package de.topobyte.formatting;

import org.junit.Assert;
import org.junit.Test;

public class TestLongFormatting
{

	@Test
	public void test()
	{
		for (long i = 0; i < 1000; i++) {
			formatAndCheck(i);
		}
		for (long i = 0; i > -1000; i--) {
			formatAndCheck(i);
		}

		formatAndCheck(Long.MAX_VALUE);
		formatAndCheck(Long.MIN_VALUE);

		// can't check for Long.MAX_VALUE in the loop condition directly
		// because of the overflow
		long last = 0;
		for (long i = 0;; i += 10000000000000L) {
			formatAndCheck(i);
			if (i < last) {
				// overflow
				break;
			}
		}

		// can't check for Long.MIN_VALUE in the loop condition directly
		// because of the underflow
		last = 0;
		for (long i = 0;; i -= 10000000000000L) {
			formatAndCheck(i);
			if (i > last) {
				// underflow
				break;
			}
		}
	}

	private void formatAndCheck(long i)
	{
		formatAndCheckLower(i);
		formatAndCheckUpper(i);
	}

	private void formatAndCheckLower(long i)
	{
		String ours = IntegerFormatting.longToHexString(i, Case.Lowercase);
		String theirs = String.format("%x", i);
		Assert.assertEquals(theirs, ours);
	}

	private void formatAndCheckUpper(long i)
	{
		String ours = IntegerFormatting.longToHexString(i, Case.Uppercase);
		String theirs = String.format("%X", i);
		Assert.assertEquals(theirs, ours);
	}

}
