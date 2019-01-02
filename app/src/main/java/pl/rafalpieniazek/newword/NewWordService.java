package pl.rafalpieniazek.newword;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

import pl.rafalpieniazek.newword.model.Element;

public class NewWordService {
    private static NewWordService instance;
    private List<Element> elements;

    private NewWordService(Context context) {
        //load

        try (InputStream open = context.getAssets().open("element.json")){
            Reader reader = new InputStreamReader(open);
            Type elementType = new TypeToken<List<Element>>() {}.getType();
            elements = new Gson().fromJson(reader, elementType);

        } catch (IOException e) {
            e.printStackTrace();
        }

//        elements.add(new Element("arbitralny", "Narzucający bezwględnie swoje zdanie."));
//        elements.add(new Element("utracjusz", "Osoba łatwo wydająca pieniądze."));
//        elements.add(new Element("egalitaryzm", "Ustrój społeczny, w którym istnieje reguła całkowitej równości wszystkich ludzi."));
//        elements.add(new Element("eudajmonizm", "Pogląd etyczny, według którego ostatecznym i najważniejszym celem każdego człowieka jest osiągnięcie szczęścia."));
//        elements.add(new Element("mitrężyć", "Marnować czas na próżno, zwlekać z czymś."));
//        elements.add(new Element("apodyktyczny", "Nie znoszący sprzeciwu i narzucający wszystkim swoją wolę."));
    }

    public static NewWordService getInstance(Context context) {
        if (instance == null) {
            instance = new NewWordService(context);
        }
        return instance;
    }

    public Element getNextRandomElement() {

        int rand = (int) (Math.random() * elements.size());
        return elements.get(rand);
    }
}
