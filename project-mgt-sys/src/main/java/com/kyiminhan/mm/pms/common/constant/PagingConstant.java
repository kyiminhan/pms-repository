package com.kyiminhan.mm.pms.common.constant;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The Interface PagingConstant.</BR>
 *
 * @author KYIMINHAN </BR>
 * @version 1.0 </BR>
 * @since Apr 4, 2019 </BR>
 *        project-mgt-sys system </BR>
 *        com.kyiminhan.mm.pms.common.constant </BR>
 *        PagingConstant.java </BR>
 */
public interface PagingConstant {

	/** The total pages. */
	String TOTAL_PAGES = "totalPages";

	/** The current page. */
	String CURRENT_PAGE = "currentPage";

	/** The next page. */
	String NEXT_PAGE = "nextPage";

	/** The previous page. */
	String PREVIOUS_PAGE = "previousPage";

	/** The paging list. */
	String PAGING_LIST = "pagingList";

	/** The number of items per page. */
	int NUMBER_OF_ITEMS_PER_PAGE = 10;

	/** The number of pages count. */
	int NUMBER_OF_PAGES_COUNT = 14;

	/** The not show page count no. */
	int NOT_SHOW_PAGE_COUNT_NO = 1;

	/** The first and last show page count no. */
	int FIRST_AND_LAST_SHOW_PAGE_COUNT_NO = 1;

	/** The not show items. */
	int NOT_SHOW_ITEMS = PagingConstant.NOT_SHOW_PAGE_COUNT_NO * 2;

	/** The middle show page count. */
	int MIDDLE_SHOW_PAGE_COUNT = PagingConstant.NUMBER_OF_PAGES_COUNT
			- (PagingConstant.FIRST_AND_LAST_SHOW_PAGE_COUNT_NO * 2);

	/**
	 * Gets the page count.
	 *
	 * @param totalPagesCount the total pages count
	 * @param currentPage     the current page
	 * @return the page count
	 */
	default Collection<Integer> getPageCount(final int totalPagesCount, final int currentPage) {
		final Collection<Integer> collection = new ArrayList<>();
		if ((totalPagesCount == 0) || (currentPage == 0) || (currentPage > totalPagesCount)) {
			return collection;
		}
		if (totalPagesCount <= PagingConstant.NUMBER_OF_PAGES_COUNT) {
			for (int i = 1; i <= totalPagesCount; i++) {
				collection.add(i);
			}
		} else if (totalPagesCount > PagingConstant.NUMBER_OF_PAGES_COUNT) {
			int firstCount = 0;
			int sum = PagingConstant.FIRST_AND_LAST_SHOW_PAGE_COUNT_NO + 1;
			for (int i = 1; i <= PagingConstant.NOT_SHOW_PAGE_COUNT_NO; i++) {
				firstCount = firstCount + sum;
				sum++;
			}
			final int lastCount = totalPagesCount
					- (PagingConstant.FIRST_AND_LAST_SHOW_PAGE_COUNT_NO + PagingConstant.NOT_SHOW_ITEMS);
			if ((currentPage - firstCount) < 4) {
				for (int i = 1; i <= (PagingConstant.NUMBER_OF_PAGES_COUNT - (PagingConstant.NOT_SHOW_PAGE_COUNT_NO
						+ PagingConstant.FIRST_AND_LAST_SHOW_PAGE_COUNT_NO)); i++) {
					collection.add(i);
				}
				collection.addAll(this.lastAbbreViation(totalPagesCount));
			} else if ((currentPage > firstCount) && (currentPage < lastCount)) {
				collection.addAll(this.firstAbbreViation());
				collection.addAll(this.firstMiddleAbbreViation(currentPage, firstCount, totalPagesCount));

			} else {
				collection.addAll(this.firstAbbreViation());
				collection.addAll(this.lastMiddleAbbreViation(totalPagesCount));
			}
		}
		return collection;
	}

	/**
	 * Last abbre viation.
	 *
	 * @param totalPagesCount the total pages count
	 * @return Collection
	 */
	default Collection<Integer> lastAbbreViation(final int totalPagesCount) {
		final Collection<Integer> collection = new ArrayList<>();
		for (int i = 1; i <= PagingConstant.NOT_SHOW_PAGE_COUNT_NO; i++) {
			collection.add(0);
		}
		for (int i = (totalPagesCount
				- (PagingConstant.FIRST_AND_LAST_SHOW_PAGE_COUNT_NO - 1)); i <= totalPagesCount; i++) {
			collection.add(i);
		}
		return collection;
	}

	/**
	 * First abbre viation.
	 *
	 * @return Collection
	 */
	default Collection<Integer> firstAbbreViation() {
		final Collection<Integer> collection = new ArrayList<>();
		for (int i = 1; i <= PagingConstant.FIRST_AND_LAST_SHOW_PAGE_COUNT_NO; i++) {
			collection.add(i);
		}
		for (int i = 1; i <= PagingConstant.NOT_SHOW_PAGE_COUNT_NO; i++) {
			collection.add(0);
		}
		return collection;
	}

	/**
	 * First middle abbre viation.
	 *
	 * @param currentPage     the current page
	 * @param firstCount      the first count
	 * @param totalPagesCount the total pages count
	 * @return Collection
	 */
	default Collection<Integer> firstMiddleAbbreViation(final int currentPage, final int firstCount,
			final int totalPagesCount) {
		final Collection<Integer> collection = new ArrayList<>();
		final int middleCount = (PagingConstant.NUMBER_OF_PAGES_COUNT
				- ((PagingConstant.FIRST_AND_LAST_SHOW_PAGE_COUNT_NO + PagingConstant.NOT_SHOW_PAGE_COUNT_NO) * 2));
		final int start = ((currentPage - firstCount) > firstCount) ? currentPage - firstCount : firstCount;
		final int loopCondition = start + middleCount;
		if ((loopCondition >= totalPagesCount)
				|| (loopCondition > (totalPagesCount - (PagingConstant.FIRST_AND_LAST_SHOW_PAGE_COUNT_NO * 2)))) {
			for (int i = (totalPagesCount - PagingConstant.NUMBER_OF_PAGES_COUNT)
					+ (PagingConstant.FIRST_AND_LAST_SHOW_PAGE_COUNT_NO + PagingConstant.NOT_SHOW_PAGE_COUNT_NO
							+ 1); i <= totalPagesCount; i++) {
				collection.add(i);
			}
		} else {
			for (int i = start; i < loopCondition; i++) {
				collection.add(i);
			}
			collection.addAll(this.lastAbbreViation(totalPagesCount));
		}
		return collection;
	}

	/**
	 * Last middle abbre viation.
	 *
	 * @param totalPagesCount the total pages count
	 * @return Collection
	 */
	default Collection<Integer> lastMiddleAbbreViation(final int totalPagesCount) {
		final Collection<Integer> collection = new ArrayList<>();
		for (int i = (totalPagesCount - PagingConstant.NUMBER_OF_PAGES_COUNT)
				+ (PagingConstant.FIRST_AND_LAST_SHOW_PAGE_COUNT_NO + PagingConstant.NOT_SHOW_PAGE_COUNT_NO
						+ 1); i <= totalPagesCount; i++) {
			collection.add(i);
		}
		return collection;
	}
}