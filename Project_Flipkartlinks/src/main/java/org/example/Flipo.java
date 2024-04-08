package org.example;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.stream.Stream;

public class Flipo{
    public static void main(String[] args) {
        try {
            Document doc = Jsoup.connect("https://www.flipkart.com/").get();
            Elements links = doc.select("a[href]");

            // Using for-each loop
            System.out.println("Using for-each loop:");
            for (Element link : links) {
                System.out.println(link.attr("href"));
            }

            // Using Stream
            System.out.println("\nUsing Stream:");
            Stream<Element> linkStream = links.stream();
            linkStream.map(link -> link.attr("href")).forEach(System.out::println);

            // Using Parallel Stream
            System.out.println("\nUsing Parallel Stream:");
            Stream<Element> parallelLinkStream = links.parallelStream();
            parallelLinkStream.map(link -> link.attr("href")).forEach(System.out::println);

            // Using Lambda Expression
            System.out.println("\nUsing Lambda Expression:");
            links.forEach(link -> System.out.println(link.attr("href")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
