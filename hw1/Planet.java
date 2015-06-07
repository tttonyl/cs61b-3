/* homework from cs61b spring 2015 
 * https://berkeley-cs61b.github.io/public_html/materials/hw/hw1/hw1.html */

public class Planet{
    double x;
    double y;
    double xVelocity;
    double yVelocity;
    double mass;
    String img;

    double xNetForce = 0.0;
    double yNetForce = 0.0;
    
    double xAccel = 0.0;
    double yAccel = 0.0;

    /* constructor for Planet*/
    public Planet(double x, double y, double xVelocity, double yVelocity, double mass, String img){
        this.x = x;
        this.y = y;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.mass = mass;
        this.img = img;
    } //close constructor

    /* calculate the distance between two Planets */ 
    public double calcDistance(Planet planet){ 
        return (Math.sqrt(Math.pow((x - planet.x), 2) + (Math.pow((y - planet.y), 2)))); 
    } //close calcDistance

    /* calculate pairwise Force */  
    public double calcPairwiseForce(Planet planet){
        return (6.67*Math.pow(10, -11) * mass * planet.mass / Math.pow(this.calcDistance(planet), 2));
    }// close calcPairwiseForce

    /* calculate the force on x dirction */
    public double calcPairwiseForceX(Planet planet){
        return this.calcPairwiseForce(planet) * Math.abs(planet.x - this.x) / this.calcDistance(planet);
    } // close calcPairwiseForceX

    /* calculate the force on y dirction */
    public double calcPairwiseForceY(Planet planet){
        return this.calcPairwiseForce(planet) * Math.abs(planet.y - this.y) / this.calcDistance(planet);
    } // close calcPairwiseForceY

    /* Net Force: The principle of superposition says that 
     * the net force acting on a particle in the x- or y-direction 
     * is the sum of the pairwise forces acting on the particle 
     * in that direction.*/ 
    public void setNetForce(Planet[] planets){
        for (int i=0; i < planets.length; i++){
            xNetForce += this.calcPairwiseForceX(planets[i]);
            yNetForce += this.calcPairwiseForceY(planets[i]);
        }
    } // close setNetForce

    /* We also want a planet to be able to draw itself at its appropriate position. */
    public void draw(){
        StdDraw.picture(this.x, this.y, "images/" + this.img);
    } // close draw

    /* update the planet */
    public void update(double dt){
         this.xAccel = this.xNetForce/ this.mass;
         this.yAccel = this.yNetForce/ this.mass;

        this.xVelocity = this.xVelocity + this.xAccel * dt;
        this.yVelocity = this.yVelocity + this.yAccel * dt;

        this.x = this.x + dt * this.xVelocity;
        this.y = this.y + dt * this.yVelocity;

    } // close update
}
