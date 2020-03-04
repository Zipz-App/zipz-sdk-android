package android.android.zlibrary.help;

public interface DrawableClickListener {
    enum DrawablePosition {TOP, BOTTOM, LEFT, RIGHT};
    void onSearchEditTextClick(DrawablePosition target);
}
