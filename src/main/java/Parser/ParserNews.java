package Parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ParserNews {
    private static Document getPage() throws IOException {
        String url = "https://tlgrm.ru/channels/news";
        Document page = Jsoup.parse(new URL(url),15000);
        return page;
    }

    public static void main(String[] args) throws IOException {
        List<Article> articleList = new LinkedList<>();
//         List<Article> articleList = new ArrayList<>();//Это главный Лист
            List<String> nameList = new ArrayList<>();
            List<String> numberList = new ArrayList<>();
            List<String> linkList = new ArrayList<>();
            List<String> descriptionList = new ArrayList<>();


            Document page = getPage();
            Element tableChannel = page.select("div[class=channel-cards-container]").first();//Беру общий доступ каналов 1 потока
            Elements h3Name = tableChannel.select("div[class=channel-card__info]");//Беру таблицу где хранятся нужные мне вещи


            Elements name = h3Name.select("h3[class=channel-card__title]");
            Elements number = h3Name.select("span[class=channel-card__subscribers]");
            Elements link = h3Name.select("a[class=channel-card__username]");
            Elements description = tableChannel.select("div[class=channel-card__description]");//Беру где хранится описание


            name.forEach(table -> {
                Element nameElement = table;
                String name0 = nameElement.text();
                nameList.add(name0);
            });
            number.forEach(table -> {
                Element numberElement = table;
                String number0 = numberElement.text();
                numberList.add(number0);
            });
            link.forEach(table -> {
                Element linkElement = table;
                String link0 = linkElement.attr("href");
                linkList.add(link0);
            });
            description.forEach(table -> {
                Element descriptionElement = table;
                String description0 = descriptionElement.text();
                descriptionList.add(description0);
            });


            for (int i = 1; i < nameList.size(); i++) {
                articleList.add(new Article(nameList.get(i), numberList.get(i), linkList.get(i), descriptionList.get(i)));
            }
        for (int i = 0; i < articleList.size(); i++) {
            System.out.println(articleList);
        }
        }

//    public List<Article> getArticleList() {
//        return articleList;
//    }
//
//    public ParserNews() {
//    }
//
//    public static List<Article> getAll(){
//        ParserNews parserNews = new ParserNews();
//        return parserNews.getArticleList();
//
//    }
}
