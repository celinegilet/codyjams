import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class CodyJams {

    public static final String NEW_LINE = "\n";
    public static final String SPACE = " ";

    public String printSalePricesByCase(List<String> lines) {

        StringBuilder sb = new StringBuilder();

        int nbCases = Integer.valueOf(lines.get(0));
        int numLine = 1;

        for (int numCase = 1; numCase <= nbCases; numCase++) {
            sb.append("Case #" + numCase + ":");
            Integer.valueOf(lines.get(numLine++));
            List<Integer> salePrices = readSalePricesForCase(lines, numLine++);
            while(!salePrices.isEmpty()) {
                Item item = new Item(salePrices.get(0));
                sb.append(SPACE + item.getSalePrice());
                salePrices.remove(item.getSalePrice());
                salePrices.remove(item.getOldSalePrice());
            }
            if (numCase < nbCases) {
                sb.append(NEW_LINE);
            }
        }
        return sb.toString();
    }

    private List<Integer> readSalePricesForCase(List<String> lines, int numLine) {
        return asList(lines.get(numLine).split(SPACE))
                .stream().map(Integer::valueOf).collect(Collectors.toList());
    }
}
