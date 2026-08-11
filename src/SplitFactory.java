import java.util.Map;

public class SplitFactory {
    private Map<SplitType,SplitStrategy> splitFactoryMap;

    public SplitFactory(Map<SplitType, SplitStrategy> splitFactoryMap) {
        this.splitFactoryMap = splitFactoryMap;
    }

    public  SplitStrategy getSplitStrategy(SplitType splitType){
        return splitFactoryMap.get(splitType);
    }
}
