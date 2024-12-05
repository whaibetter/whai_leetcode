package cn.whaifree.tech.loadBalance;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/12/4 22:10
 * @注释
 */
public class SelfDef {

    static List<Node> serverMap = new ArrayList<>();

    static {
        serverMap.add(new Node("server1"));
        serverMap.add(new Node("server2"));
        serverMap.add(new Node("server3"));
    }

    static class Node{
        String serverName;
        public Node(String serverName) {
            this.serverName = serverName;
        }

        String getServerName() {
            return serverName;
        }
    }

    static class Server{
        RuleInter myRule;
        public Server(RuleInter rule) {
            this.myRule = rule;
        }
        public Node getServer(String param) {
            return myRule.getNode(param);
        }
    }

    static class HashRule implements RuleInter{
        @Override
        public SelfDef.Node getNode(String param) {
            int i = param.hashCode() % serverMap.size();
            return serverMap.get(i);
        }
    }


    public static void main(String[] args) {
        Server server = new Server(new HashRule());
        for (int i = 0; i < 100; i++) {
            SelfDef.Node node = server.getServer(String.valueOf(i));
            System.out.println(node.getServerName());
        }
    }

}

interface RuleInter{
    SelfDef.Node getNode(String param);
}



