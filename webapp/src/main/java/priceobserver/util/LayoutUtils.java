package priceobserver.util;

import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public final class LayoutUtils {

    public static final String SELECTED_PAGE_ATTR = "selectedPage";
    public static final String PAGE_LIST_ATTR = "pageList";
    public static final String PREVIOUS_PAGE_ATTR = "previousPage";
    public static final String NEXT_PAGE_ATTR = "nextPage";

    public static final String THREE_DOTS = "...";
    public static final String FIRST = "1";
    public static final String SECOND = "2";
    public static final String THIRD = "3";
    public static final String FOURTH = "4";
    public static final String FIFTH = "5";

    public static final String PRODUCT_PAGE = "product";
    public static final String PRODUCT_LIST_PAGE = "productsList";
    public static final String SEARCH_RESULT_LIST_PAGE = "searchResultList";
    public static final String PROFILE_PAGE = "profile";
    public static final String LOGIN_PAGE = "login";
    public static final String PRODUCT_NOT_FOUND_MESSAGE = "Oops, the product you're looking for isn't found";
    public static final byte NUMBER_OF_PRODUCTS_PER_PAGE_AT_A_TIME = 9;

    private LayoutUtils() {

    }

    public static void preparePagination(Model model, Integer selectedPage, int countOfPages) {
        model.addAttribute(SELECTED_PAGE_ATTR, selectedPage.toString());
        model.addAttribute(PAGE_LIST_ATTR, LayoutUtils.getPaginationList(selectedPage, countOfPages));
        model.addAttribute(PREVIOUS_PAGE_ATTR, selectedPage - 1 < 1 ? "" : String.valueOf(selectedPage - 1));
        model.addAttribute(NEXT_PAGE_ATTR, selectedPage + 1 > countOfPages ? "" : String.valueOf(selectedPage + 1));
    }

    private static List<String> getPaginationList(int selectedPage, int countOfPages) {
        if (selectedPage < 1 || countOfPages < 1) {
            throw new IllegalArgumentException("Count of pages or selected page can't be less than 1.");
        }

        List<String> pageList = new ArrayList<>();
        if (countOfPages < 8) {
            IntStream.range(1, countOfPages + 1).forEach(i -> pageList.add(String.valueOf(i)));
            return pageList;
        }

        pageList.add(FIRST);
        if (selectedPage == 1) {
            for (int i = 2; i < 6; i++) {
                pageList.add(String.valueOf(i));
            }
            pageList.add(THREE_DOTS);
        } else if(selectedPage == countOfPages) {
            for (int i = countOfPages - 4; i < countOfPages; i++) {
                pageList.add(String.valueOf(i));
            }
            pageList.add(1, THREE_DOTS);
        } else if (selectedPage <= 4) {
            pageList.addAll(
                    List.of(SECOND, THIRD, FOURTH, FIFTH, THREE_DOTS)
            );
        } else if (selectedPage >= countOfPages - 3) {
            pageList.addAll(
                    List.of(THREE_DOTS,
                            String.valueOf(countOfPages - 4),
                            String.valueOf(countOfPages - 3),
                            String.valueOf(countOfPages - 2),
                            String.valueOf(countOfPages - 1))
            );
        } else {
            pageList.addAll(List.of(THREE_DOTS,
                    String.valueOf(selectedPage - 1),
                    String.valueOf(selectedPage),
                    String.valueOf(selectedPage + 1),
                    THREE_DOTS)
            );

        }
        pageList.add(String.valueOf(countOfPages));
        return pageList;
    }
}
