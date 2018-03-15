import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
/**
 * Write a description of class Collision here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Collision  
{
    private Set<Collider> collidingObjects;

    /**
     * Constructor for objects of class Collision
     */
    public Collision(List<Collider> collidingObjects)
    {
        this.collidingObjects = new HashSet<>(collidingObjects);
    }

    public Collision(Collider obj1, List<Collider> otherObjs)
    {
        this.collidingObjects = new HashSet<>();
        collidingObjects.add(obj1);
        collidingObjects.addAll(otherObjs);
    }
    
    public void processCollision(){
        int xVector = 0;
        int yVector = 0;
        for(Collider collider:collidingObjects){
            xVector += collider.getDeltaX()*collider.getSize();
            yVector += collider.getDeltaY()*collider.getSize();
        }
        for(Collider collider:collidingObjects){
            //collider.setDeltaX(collider.getDeltaX()+xVector/collider.getSize());
            //collider.setDeltaY(collider.getDeltaY()+xVector/collider.getSize());
            //collider.newPosition();
        }
    }
    
    public void addCollider(Collider c){
        collidingObjects.add(c);
    }
    
    public Set<Collider> getCollidingObjects(){
        return collidingObjects;
    }
}
