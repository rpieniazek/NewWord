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

import pl.rafalpieniazek.newword.model.FlashCard;

public class FlashCardService {
    private static FlashCardService instance;
    private List<FlashCard> flashCards;

    private FlashCardService(Context context) {
        loadElementsFromAsset(context);
    }

    private void loadElementsFromAsset(Context context) {
        try (InputStream open = context.getAssets().open("element.json")){
            Reader reader = new InputStreamReader(open);
            Type elementType = new TypeToken<List<FlashCard>>() {}.getType();
            flashCards = new Gson().fromJson(reader, elementType);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static FlashCardService getInstance(Context context) {
        if (instance == null) {
            instance = new FlashCardService(context);
        }
        return instance;
    }

    public FlashCard getNextRandomElement() {

        int rand = (int) (Math.random() * flashCards.size());
        return flashCards.get(rand);
    }
}
