package swp.charite;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.io.IOException;

public class NullZooKeeperWatcher implements Watcher {

    @Override
    public void process(WatchedEvent event) {
        Event.KeeperState state = event.getState();
        switch (state) {
            case Expired:
                try {
                    CustomTransformation.closeZooKeeper();
                    CustomTransformation.initZooKeeper();
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
}
