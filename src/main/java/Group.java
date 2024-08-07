public enum Group {

     VPD ("VPD"),
     RCMP ("RCMP"),
     VPA ("VPA"),
     CCG ("CGC"),
     TC ("TC");

     private final String name;

     Group(String g) {
          name = g;
     }

     public String toString(){
          return this.name;
     }

     // Static method to get Group by String value
     public static Group fromString(String name) {
          for (Group group : Group.values()) {
               if (group.name.equalsIgnoreCase(name)) {
                    return group;
               }
          }
          throw new IllegalArgumentException("No enum constant with name " + name);
     }

}
