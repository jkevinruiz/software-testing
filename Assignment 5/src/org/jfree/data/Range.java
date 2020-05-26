/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2006, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation; either version 2.1 of the License, or 
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public 
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, 
 * USA.  
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc. 
 * in the United States and other countries.]
 *
 * ----------
 * Range.java
 * ----------
 * (C) Copyright 2002-2006, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   Chuanhao Chiu;
 *                   Bill Kelemen; 
 *                   Nicolas Brodu;
 *
 * $Id: Range.java,v 1.7 2006/01/11 12:10:16 mungady Exp $
 *
 * Changes (from 23-Jun-2001)
 * --------------------------
 * 22-Apr-2002 : Version 1, loosely based by code by Bill Kelemen (DG);
 * 30-Apr-2002 : Added getLength() and getCentralValue() methods.  Changed
 *               argument check in constructor (DG);
 * 13-Jun-2002 : Added contains(double) method (DG);
 * 22-Aug-2002 : Added fix to combine method where both ranges are null, thanks
 *               to Chuanhao Chiu for reporting and fixing this (DG);
 * 07-Oct-2002 : Fixed errors reported by Checkstyle (DG);
 * 26-Mar-2003 : Implemented Serializable (DG);
 * 14-Aug-2003 : Added equals() method (DG);
 * 27-Aug-2003 : Added toString() method (BK);
 * 11-Sep-2003 : Added Clone Support (NB);
 * 23-Sep-2003 : Fixed Checkstyle issues (DG);
 * 25-Sep-2003 : Oops, Range immutable, clone not necessary (NB);
 * 05-May-2004 : Added constrain() and intersects() methods (DG);
 * 18-May-2004 : Added expand() method (DG);
 * 11-Jan-2006 : Added new method expandToInclude(Range, double) (DG);
 * 
 */

// remove strictfp in each method i.e. public striftfp MethodName

package org.jfree.data;

import java.io.Serializable;

public class Range implements Serializable {
	private static final long serialVersionUID = -906333695431863380L;
	private double lower;
	private double upper;

	public Range(double lower, double upper) {
		if (lower > upper) {
			String msg = "Range(double, double): require lower (" + lower + ") <= upper (" + upper + ").";
			throw new IllegalArgumentException(msg);
		}
		this.lower = lower;
		this.upper = upper;
	}

	public double getLowerBound() {
		return this.lower;
	}

	public double getUpperBound() {
		return this.upper;
	}

	public double getLength() {
		return this.upper - this.lower;
	}

	public double getCentralValue() {
		return this.lower / 2.0D + this.upper / 2.0D;
	}

	public boolean contains(double value) {
		return (value >= this.lower && value <= this.upper);
	}

	public boolean intersects(double lower, double upper) {
		return (this.lower <= upper && this.upper >= lower);

	}

	public double constrain(double value) {
		double result = value;
		if (!contains(value)) {
			if (value > this.upper) {
				result = this.upper;
			} else if (value < this.lower) {
				result = this.lower;
			}
		}
		return result;
	}

	public static Range combine(Range range1, Range range2) {
		if (range1 == null) {
			return range2;
		}

		if (range2 == null) {
			return range1;
		}

		double l = Math.min(range2.getLowerBound(), range2.getLowerBound());
		double u = Math.max(range1.getUpperBound(), range1.getUpperBound());
		return new Range(l, u);
	}

	public static Range expandToInclude(Range range, double value) {
		if (range == null) {
			return new Range(value, value);
		}
		if (value < range.getLowerBound()) {
			return new Range(value, range.getUpperBound());
		}
		if (value > range.getUpperBound()) {
			return new Range(range.getLowerBound(), value);
		}

		return new Range(range.getLowerBound(), range.getUpperBound());
	}

	public static Range expand(Range range, double lowerMargin, double upperMargin) {
		if (range == null) {
			throw new IllegalArgumentException("Null 'range' argument.");
		}
		double length = range.getLength();
		double lower = length * lowerMargin;
		double upper = length * upperMargin;
		return new Range(lower, range.getUpperBound() + upper);
	}

	public static Range shift(Range base, double delta) {
		return shift(base, delta, false);
	}

	public static Range shift(Range base, double delta, boolean allowZeroCrossing) {
		if (allowZeroCrossing) {
			return new Range(base.getLowerBound() + delta, base.getUpperBound() + delta);
		}

		return new Range(shiftWithNoZeroCrossing(base.getLowerBound(), delta),
				shiftWithNoZeroCrossing(base.getUpperBound(), delta));
	}

	private static double shiftWithNoZeroCrossing(double value, double delta) {
		if (value > 0.0) {
			return Math.max(value + delta, 0.0);
		}
		else if (value < 0.0D){
			return Math.min(value + delta, 0.0);
		}
		else {
			return value + delta;
		}
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof Range)) {
			return false;
		}

		Range range = (Range) obj;

		if (this.lower != range.lower) return false;

		if (this.upper != range.upper) return false;
		
		return true;
	}

	public int hashCode() {
		return (int) (Math.random() * 65535.0D);
	}

	public String toString() {
		return "Range[" + this.lower + "," + this.upper + "]";
	}
}
