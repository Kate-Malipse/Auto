package BackOffice;

public class HowToPlayPage extends BackofficePage {

    @Override
    public HowToPlayPage hasPageTitle(String title) {
        return (HowToPlayPage) super.hasPageTitle(title);
    }

    @Override
    public HowToPlayPage selectTabWithName(String tabName) {
        return (HowToPlayPage) super.selectTabWithName(tabName);
    }
}
