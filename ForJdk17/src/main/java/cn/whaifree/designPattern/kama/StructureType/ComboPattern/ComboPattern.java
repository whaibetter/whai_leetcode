package cn.whaifree.designPattern.kama.StructureType.ComboPattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/5 19:11
 * @注释
 */
public class ComboPattern {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CompanyComposite company = new CompanyComposite(sc.next());
        DepartmentComposite departmentComposite = null;
        int n = sc.nextInt();
        while(n > 0){
            String type = sc.next();
            if(type.equals("D")){
                String department = sc.next();
                departmentComposite = new DepartmentComposite(department);
                company.add(departmentComposite);
            }else{
                String personnel = sc.next();
                PersonnelLeaf personnelLeaf = new PersonnelLeaf(personnel);
                if(departmentComposite != null) {
                    departmentComposite.add(personnelLeaf);
                }
            }
            n--;
        }
        System.out.println("Company Structure:");
        company.print();
    }

    // Component
    interface Component{
        void print();
    }

    // Leaf
    static class PersonnelLeaf implements Component{
        private String name;

        public PersonnelLeaf(String name){
            this.name = name;
        }

        @Override
        public void print() {
            System.out.println("    "+name);
        }
    }

    // Composite
    // 部门
    static class DepartmentComposite implements Component{
        private String name;
        private List<Component> list = new ArrayList<>();

        public DepartmentComposite(String name){
            this.name = name;
        }

        public void add(Component component){
            list.add(component);
        }

        @Override
        public void print() {
            System.out.println("  " + name);
            for(Component component : list){
                component.print();
            }
        }
    }

    // 公司-- 下面包括多个子类（部门-->员工）
    static class CompanyComposite implements Component{
        private String name;
        private List<Component> list = new ArrayList<>();

        public CompanyComposite(String name){
            this.name = name;
        }

        public void add(Component component){
            list.add(component);
        }

        @Override
        public void print() {
            System.out.println(name);
            for(Component component : list){
                component.print();
            }
        }
    }
}
