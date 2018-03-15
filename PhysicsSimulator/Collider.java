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
    static int numberOfColliders = 0;
    int index;

    
    public Collider(int size, int deltaX, int deltaY){
        this.size = size;
        deltaX = deltaX;
        deltaY = deltaY;
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
        newPosition();
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
        List<Collider>collidingObjects = (List<Collider>)getIntersectingObjects(Collider.class);
                            
        if(!collidingObjects.isEmpty()){
            cm.addCollidingObjects(this, collidingObjects);
        }
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
    
    public void newPosition(){
        setLocation(getX() + deltaX, getY() + deltaY);
    }
    
    @Override
    public boolean equals(Object other){
        if(!(other instanceof Collider))
            return false;
        Collider otherCollider = (Collider)other;
        return this.index == otherCollider.index;
    }
    
    @Override
    public int hashCode(){
        int result = 17;
        result = result * 31 + size;
        result = result * 31 + index;
        result = result * 31 + pw.hashCode();
        result = result * 31 + cm.hashCode();
        return result;
    }
}
