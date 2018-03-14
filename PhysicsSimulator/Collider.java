import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collections;
/**
 * Write a description of class Collider here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Collider extends Actor
{
    int size;
    GreenfootImage img;
    int deltaX;
    int deltaY;
    Random rnd = new Random();
    PhysicsWorld pw;
    CollisionManager cm;
    int worldWidth;
    int worldHeight;
    List<Collider> ongoingCollisions;
    List<Collider> collidingObjects;
    int ongoingCollisionsSize = 0;
    int collidingObjectsSize = 0;
    static int numberOfColliders = 0;
    int index;
    public Collider(int size){
        this.size = size;
        ongoingCollisions = new ArrayList<>();
        deltaX = rnd.nextInt(10)-5;
        deltaY = rnd.nextInt(10)-5;
        img = new GreenfootImage(size,size);
        img.setColor(
            new Color(
                rnd.nextInt(255),
                rnd.nextInt(255),
                rnd.nextInt(255)
            )
        );
        img.fillOval(0, 0, size, size);
        setImage(img);
        this.index = numberOfColliders;
        numberOfColliders++;
    }
    
    public void addedToWorld(World w){
        pw = (PhysicsWorld)getWorld();
        cm = pw.getCollisionManager();
        worldWidth = pw.getWidth();
        worldHeight = pw.getHeight();
    }
    
    /**
     * Act - do whatever the Collider wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        setLocation(getX() + deltaX, getY() + deltaY);
        if(getX()<=size && deltaX < 0){
            deltaX*=-1;
        }
        if(getX()>=worldWidth-size && deltaX > 0){
            deltaX*= -1;
        }
        if(getY()<=size && deltaY<0){
            deltaY *= -1;
        }
        if(getY()>=worldHeight-size && deltaY>0){
            deltaY *= -1;
        }
        collidingObjects = (List<Collider>)getIntersectingObjects(Collider.class);
        collidingObjectsSize = collidingObjects.size();
        List<Collider> collidingObjectsFinal = new ArrayList();
        Collections.copy(collidingObjects, collidingObjectsFinal);
        List<Collider> ongoingCollisionsFinal = new ArrayList();
        Collections.copy(ongoingCollisions, ongoingCollisionsFinal);
        collidingObjects = collidingObjects.stream().
                            filter(co->!(ongoingCollisionsFinal.contains(co))).
                            collect(Collectors.toList());
        ongoingCollisionsSize = ongoingCollisions.size();
        ongoingCollisions = ongoingCollisions.stream().
                            filter(oc->!(collidingObjectsFinal.contains(oc))).
                            collect(Collectors.toList());
        
        collidingObjectsSize = collidingObjects.size();
                            
        if(!collidingObjects.isEmpty()){
            cm.addCollidingObjects(this, collidingObjects);
        }
        ongoingCollisions.addAll(collidingObjects);
        ongoingCollisionsSize = ongoingCollisions.size();
        collidingObjects.clear();
    }    
    
    public void setDeltaX(int newDeltaX){
        deltaX = newDeltaX;
    }
    public void setDeltaY(int newDeltaY){
        deltaY = newDeltaY;
    }
    public int getDeltaX(){
        return deltaX;
    }
    public int getDeltaY(){
        return deltaY;
    }
    public int getSize(){
        return size;
    }
    
    @Override
    public boolean equals(Object other){
        if(!(other instanceof Collider))
            return false;
        Collider otherCollider = (Collider)other;
        return this.index == otherCollider.index;
    }
}
