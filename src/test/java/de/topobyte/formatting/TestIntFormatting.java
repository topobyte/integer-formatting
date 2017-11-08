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

public class TestIntFormatting
{

	@Test
	public void test()
	{
		for (int i = 0; i < 1000; i++) {
			formatAndCheck(i);
		}
		for (int i = 0; i > -1000; i--) {
			formatAndCheck(i);
		}

		formatAndCheck(Integer.MAX_VALUE);
		formatAndCheck(Integer.MIN_VALUE);

		// can't check for Integer.MAX_VALUE in the loop condition directly
		// because of the overflow
		int last = 0;
		for (int i = 0;; i += 10000) {
			formatAndCheck(i);
			if (i < last) {
				// overflow
				break;
			}
		}

		// can't check for Integer.MIN_VALUE in the loop condition directly
		// because of the underflow
		last = 0;
		for (int i = 0;; i -= 10000) {
			formatAndCheck(i);
			if (i > last) {
				// underflow
				break;
			}
		}
	}

	private void formatAndCheck(int i)
	{
		formatAndCheckLowerHex(i);
		formatAndCheckUpperHex(i);
	}

	private void formatAndCheckLowerHex(int i)
	{
		String ours = IntegerFormatting.intToHexString(i, Case.Lowercase);
		String theirs = String.format("%x", i);
		Assert.assertEquals(theirs, ours);
	}

	private void formatAndCheckUpperHex(int i)
	{
		String ours = IntegerFormatting.intToHexString(i, Case.Uppercase);
		String theirs = String.format("%X", i);
		Assert.assertEquals(theirs, ours);
	}
}
