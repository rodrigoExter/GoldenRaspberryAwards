package com.base.util.pageable;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableCustom  implements Pageable{

	 private final int offset;//first record is 0
	    private final int size;
	    private final Sort sort;

	    public PageableCustom(int offset, int size, Sort sort) {
	        if (offset < 0) {
	        	offset = 0;
	        }
	        if (size < 1) {
	        	size = 1;
	        }
	        this.offset = offset*size;
	        this.size = size;
	        this.sort = sort;
	    }

	    public int getPageNumber() {
	        return offset / size;//TBD
	    }

	    public Pageable next() {
	        return new PageableCustom(offset+size, size, sort);
	    }

	    public Pageable previousOrFirst() {
	        int prevoffset = offset-size;//TBD
	        return new PageableCustom((prevoffset<0?0:prevoffset), size, sort);
	    }

	    public Pageable first() {
	        return new PageableCustom(0, size, sort);
	    }

	    public boolean hasPrevious() {
	        return offset > 0;
	    }

	    public int getOffset() {
	        return offset;
	    }

	    public int getPageSize() {
	        return size;
	    }

	    public Sort getSort() {
	        return sort;
	    }
}