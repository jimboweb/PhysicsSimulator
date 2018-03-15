import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhysicsWorld extends World
{
    CollisionManager cm;
    Class[] actOrder = {Collider.class, PhysicsWorld.class};
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public PhysicsWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        cm = new CollisionManager();
        setActOrder(actOrder);
        prepare();
    }

    public CollisionManager getCollisionManager(){
        return cm;

    }

    public void act(){
        cm.processAllCollisions();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Collider collider = new Collider(75);
        addObject(collider,256,236);
        Collider collider2 = new Collider(100);
        addObject(collider2,250,150);
    }
}
