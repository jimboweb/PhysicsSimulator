import java.util.ArrayList;
import java.util.List;
import java.util.Set;
/**
 * Write a description of class CollisionManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CollisionManager  
{
    
    List<Collision> collisions;
    /**
     * Constructor for objects of class CollisionManager
     */
    public CollisionManager()
    {
        collisions = new ArrayList<>();
    }
    
    public void addCollidingObjects(Collider obj1, List<Collider> otherObjs){
        for(Collision collision:collisions){
            Set<Collider> colliders = collision.getCollidingObjects();
            boolean hasObject = false;
            if(colliders.contains(obj1)){
                hasObject = true;
            }
            for(Collider otherObj:otherObjs){
                if(colliders.contains(otherObj)){
                    hasObject = true;
                    break;
                }
            }
            if(hasObject){
                colliders.add(obj1);
                colliders.addAll(otherObjs);
                return;
            }
        }
        collisions.add(new Collision(obj1, otherObjs));
    }
    
    public void processAllCollisions(){
        for(Collision collision:collisions){
            collision.processCollision();
        }
        collisions.clear();
    }
}
