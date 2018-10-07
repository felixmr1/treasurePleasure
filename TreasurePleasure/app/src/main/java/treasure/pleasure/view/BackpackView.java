package treasure.pleasure.view;

import java.util.ArrayList;

/**
 * BackpackView exposes methods to be used by BackpackPresenter
 * @author David
*/
public interface BackpackView {
  void displayContent(ArrayList<Integer> contentToDisplayList);
}
