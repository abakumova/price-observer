package priceobserver.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class LayoutUtils {

    private LayoutUtils() {
        //prevent object of utils classes
    }

    public static List<String> getPaginationList(int selectedPage, int countOfPages) {
        if (selectedPage < 1 || countOfPages < 1) {
            throw new IllegalArgumentException("Count of pages or selected page can't be less than 1.");
        }

        List<String> pageList = new ArrayList<>();
        if (countOfPages < 8) {
            IntStream.range(1, countOfPages + 1).forEach(i -> pageList.add(String.valueOf(i)));
            return pageList;
        }

        pageList.add("1");

        if (selectedPage == 1) {
            for (int i = 2; i < 6; i++) {
                pageList.add(String.valueOf(i));
            }
            pageList.add("...");
        } else if(selectedPage == countOfPages) {
            for (int i = countOfPages - 4; i < countOfPages; i++) {
                pageList.add(String.valueOf(i));
            }
            pageList.add(1, "...");
        } else if (selectedPage <= 4) {
            pageList.addAll(
                    List.of("2","3", "4", "5", "...")
            );
        } else if (selectedPage >= countOfPages - 3) {
            pageList.addAll(
                    List.of("...",
                            String.valueOf(countOfPages - 4),
                            String.valueOf(countOfPages - 3),
                            String.valueOf(countOfPages - 2),
                            String.valueOf(countOfPages - 1))
            );
        } else {
            pageList.addAll(List.of("...",
                    String.valueOf(selectedPage - 1),
                    String.valueOf(selectedPage),
                    String.valueOf(selectedPage + 1),
                    "...")
            );

        }
        pageList.add(String.valueOf(countOfPages));
        return pageList;
    }
}
