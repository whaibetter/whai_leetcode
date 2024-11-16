package cn.whaifree.interview.ct;

import java.util.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/13 21:18
 * @注释
 */
public class P1 {

    // Method to parse configurations
    private static void parseConfig(List<String> lines, Set<String> physical, Map<String, List<String>> bonds, Map<String, String> vlans) {
        for (String line : lines) {
            String[] parts = line.split(" ");
            switch (parts[0]) {
                case "Physical":
                    physical.add(parts[1]);
                    break;
                case "Bond":
                    String bondName = parts[1];
                    List<String> children = new ArrayList<>(Arrays.asList(parts).subList(3, parts.length));
                    Collections.sort(children);
                    bonds.put(bondName, children);
                    break;
                case "VLAN":
                    vlans.put(parts[1], parts[2]);
                    break;
            }
        }
    }

    // Method to calculate minimum steps
    public static int calculateMinSteps(List<String> currentLines, List<String> targetLines) {
        // Parsing current and target configurations
        Set<String> currentPhysical = new HashSet<>();
        Map<String, List<String>> currentBonds = new HashMap<>();
        Map<String, String> currentVlans = new HashMap<>();
        parseConfig(currentLines, currentPhysical, currentBonds, currentVlans);

        Set<String> targetPhysical = new HashSet<>();
        Map<String, List<String>> targetBonds = new HashMap<>();
        Map<String, String> targetVlans = new HashMap<>();
        parseConfig(targetLines, targetPhysical, targetBonds, targetVlans);

        int steps = 0;

        // Step 1: Delete extra bonds and vlans in the current configuration
        for (String bond : new HashSet<>(currentBonds.keySet())) {
            if (!targetBonds.containsKey(bond)) {
                currentBonds.remove(bond);
                steps++;
            }
        }
        for (String vlan : new HashSet<>(currentVlans.keySet())) {
            if (!targetVlans.containsKey(vlan)) {
                currentVlans.remove(vlan);
                steps++;
            }
        }

        // Step 2: Add missing bonds and vlans in the current configuration
        for (Map.Entry<String, List<String>> entry : targetBonds.entrySet()) {
            String bond = entry.getKey();
            List<String> targetChildren = entry.getValue();
            if (!currentBonds.containsKey(bond)) {
                currentBonds.put(bond, targetChildren);
                steps++;
            } else if (!currentBonds.get(bond).equals(targetChildren)) {
                currentBonds.put(bond, targetChildren);
                steps++;
            }
        }

        for (Map.Entry<String, String> entry : targetVlans.entrySet()) {
            String vlan = entry.getKey();
            String targetParent = entry.getValue();
            if (!currentVlans.containsKey(vlan)) {
                currentVlans.put(vlan, targetParent);
                steps++;
            } else if (!currentVlans.get(vlan).equals(targetParent)) {
                currentVlans.put(vlan, targetParent);
                steps++;
            }
        }

        return steps;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        List<String> currentLines = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            currentLines.add(scanner.nextLine().trim());
        }

        int m = Integer.parseInt(scanner.nextLine());
        List<String> targetLines = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            targetLines.add(scanner.nextLine().trim());
        }

        int result = calculateMinSteps(currentLines, targetLines);
        System.out.println(result);

        scanner.close();
    }

}
