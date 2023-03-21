import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.regex.Pattern;

public class SiteMap extends RecursiveTask<List<String>> {
    private String URL;
    static int n;

    public List<String> getList() {
        return list;
    }

    private List<String> list;
    private String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
    ;

    public SiteMap(String URL) {
        this.URL = URL;
        list = new ArrayList<>();
    }


    protected List<String> compute() {
        List<ForkJoinTask<List<String>>> tasks = new ArrayList<>();
        if (Pattern.matches(regex, URL)) {
            //   System.out.println("In compute()");
            try {
                Document doc = Jsoup.connect(URL).get();
                Elements elems = doc.select("a[abs:href]");
                for (Element elem : elems) {
                    Thread.sleep(100);
                    String nextURL = elem.attr("abs:href");
                    if (Pattern.matches(regex, nextURL) && nextURL.contains(URL)
                            && !nextURL.equals(URL)) {
                        n++;
                        System.out.println(n +"  "+ nextURL);
                        SiteMap sm = new SiteMap(nextURL);
                        tasks.add(sm.fork());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for(ForkJoinTask<List<String>> tsk: tasks){
            list.add(tsk.join().toString());
        }
        return list;
    }

}
