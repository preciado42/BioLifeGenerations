/*
 * Attempt at creating a simulation of populations and generations that follow
 * simple rules and hopefully "learn" to behave in a way that ensures their 
 * survival in the simulated world.
 */
package biolifegenerations;

/**
 *
 * @author Angel Preciado
 */
public class BioLifeGenerations {

    private Environment env;
    
    public void run(){
        env = new Environment();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        BioLifeGenerations myGen = new BioLifeGenerations();
        myGen.run();
    }
}
