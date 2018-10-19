package treasure.pleasure.data;

import android.content.Context;
import android.content.SharedPreferences;

public class PersistentData {

  private static final String PREF_NAME = "sharedPrefs";
  private static final String HIGHSCORE = "high_score_key";

  private static SharedPreferences getPref(Context context) {
    return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
  }

  public static int getHighScore(Context context) {
    //param 1 is the key, param 2 is default vale.
    return getPref(context).getInt(HIGHSCORE, 0);
  }

  public static void saveHighScore(Context context, int input) {
    SharedPreferences.Editor editor = getPref(context).edit();
    editor.putInt(HIGHSCORE, input);
    editor.commit();
  }
}
