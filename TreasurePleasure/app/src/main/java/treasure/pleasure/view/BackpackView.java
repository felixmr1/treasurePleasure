package treasure.pleasure.view;

import treasure.pleasure.presenter.BackpackPresenterImpl.ItemPathAndValue;
import java.util.List;

/**
 * BackpackView exposes methods to be used by BackpackPresenter
 * @author David
*/
public interface BackpackView {
  void displayContent(List<ItemPathAndValue> contentToDisplayList);
}
