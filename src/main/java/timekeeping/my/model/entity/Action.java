package timekeeping.my.model.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 *  Data storage about spent time for some activity.
 * */
public class Action extends Entity implements Serializable {

    private User user;
    private Activity activity;
    private Integer spentLastSession;
    private Integer totalSpent;
    private Access access;

    public Action() {
        spentLastSession = 0;
        totalSpent = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Action action = (Action) o;
        return user.equals(action.user) && activity.equals(action.activity) && totalSpent.equals(action.totalSpent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user, activity, totalSpent);
    }

    public Access getAccess() {
        return access;
    }

    public void setAccess(Access access) {
        this.access = access;
    }

    public User getUser() {
        return user;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setSpentLastSession(Integer spentLastSession) {
        this.spentLastSession = spentLastSession;
    }

    public void setTotalSpent(Integer totalSpent) {
        this.totalSpent = totalSpent;
    }

    public Integer getLast() {
        return spentLastSession;
    }

    public Integer getTotal() {
        return totalSpent;
    }

    public void updateTotal() {
        this.totalSpent = totalSpent + spentLastSession;
    }

    @Override
    public String toString() {
        return "Action{" +
                "user=" + user +
                ", activity=" + activity +
                ", spentLastSession=" + spentLastSession +
                ", totalSpent=" + totalSpent +
                ", access=" + access +
                '}';
    }

}
