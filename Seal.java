import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Seal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Seal extends Actor
{
    /**
     * Act - do whatever the Seal wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootSound roar = new GreenfootSound("scream.mp3");
    GreenfootImage[] idle = new GreenfootImage[4];
    
    SimpleTimer animationTimer = new SimpleTimer();
    
    public Seal()
    {
        for(int i = 0; i < idle.length; i++)
        {
            idle[i] = new GreenfootImage("images/sealsprites/idle" + i + ".png");
        }
        setImage(idle[0]);
        animationTimer.mark();
    }
    int imageIndex = 0;
    public void animate()
    {
        if(animationTimer.millisElapsed() < 500)
        {
            return;
        }
        animationTimer.mark();
        setImage(idle[imageIndex]);
        imageIndex = (imageIndex + 1) % idle.length;
        
    }
    public void act()
    {
        // Add your action code here.
    
        if(Greenfoot.isKeyDown("up"))
        {
            setLocation(getX(), getY()-5);
        }
        if(Greenfoot.isKeyDown("down"))
        {
            setLocation(getX(), getY()+5);
        }
        if(Greenfoot.isKeyDown("left"))
        {
            setLocation(getX()-5, getY());
        }
        if(Greenfoot.isKeyDown("right"))
        {
            setLocation(getX()+5, getY());
        }
        eat();
        animate();
    }
    public void eat()
    {
        if(isTouching(Bread.class))
        {
            removeTouching(Bread.class);
            MyWorld world = (MyWorld)getWorld();
            world.spawnBread();
            world.increaseScore();
            roar.play();
        }
        
    }

}
