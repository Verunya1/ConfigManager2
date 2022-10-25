import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class Parser {

    private static Document getName(String packetName,int curL) throws IOException {
        Document document = Jsoup.connect("https://www.npmjs.com/package/" + packetName + "?activeTab=dependencies").get();
        Elements dependencies = document.getElementsByAttributeValue("aria-label","Dependencies");
        String temp="";
        for(int i = 0;i<curL;i++)
            temp+="     ";
        for (Element element:dependencies){
            for (Element el:element.children()) {
                System.out.println(temp+packetName+" - > "+el.text());
                getName(el.text(),curL+1);
            }

        }
//        dependencies.forEach(element -> System.out.println(element.getElementsByClass("_75a5f581 f4 fw6 fl db pv1 ma1 black-70 link hover-black animate")));
        return document;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        System.out.println("Digraph { ");
        getName(str,1);
        System.out.println("}");



    }
}
