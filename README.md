# Advanced-Paint

The program uses partial MVC as a structure. The different design patterns have been used such as Factory, Strategy and others to implement the paint. Different shapes were designed and implemented. Shapes feedback (user can see as it draws) was also made possoble. Different java libraries and tools were used to implement the features such as whole Colour Panel, Resolution, Save/Open, Symmetry and Thinckness for most of the shapes. The program advanced paint is developed as a team. The instructor provided with a skeleton of basic code, notably the scribble package which is used by paint package. 

##High Level Architecture:

**Model:**
To keep the code simple, a partial MVC (Model, View and Controller) was implemented.
Model. The Model captures enough information to re-draw the sketch of
the user.
	
**View & Controller:**
There is a View class as well as a few, what we call, view components.
The View class capturing the JFrame as well
as containing all of its components.
View components are meant to be contained in the main View.
Examples of view components are PaintPanel and ShapeChooserPanel.
	
The View class, and view components all implements their own controller.
For example, the PaintPanel listens to its own MouseEvents.
This is instead of separating out the Controller from the View. This choice
was made to simplify the code, yet still keep it modular and clear.

Each view component can see the containing View as well as the Model
as appropriate. This means that each view component can speak to the
other view components. An example of this is when the ShapeChooserPanel
buttons are clicked, resulting in a mode change in the PaintPanel.
	
