public class RegExMatchWithNFA {    

    //build a NFA 
    public List<State> buildAutomaton(String pattern){
            List<State> states = new ArrayList<>();
        //initial state
        states.add(new State(0,false));

        int i = 0;
        while ( i <pattern.length() ){
            char currentChar = pattern.charAt(i);
            //create new state to handle our new transition 
            states.add(new State(states.size(),false));
            
            //create the transitions
                List<Character> trans=null;
            //'.'' is transform to range a-z
            if (currentChar=='.'){
                trans = IntStream.rangeClosed('a', 'z').boxed().map(e -> (char)e.intValue()).collect(Collectors.toList());
            }else {
                trans = Arrays.asList(currentChar);
            }
            
            //if star 
            if (i+1<pattern.length() && pattern.charAt(i+1)=='*'){
                //loop over our current state
                states.get(states.size()-2).transitions.add(new Transition(trans,states.size()-2));
                //epsilon transition to next state
                    states.get(states.size()-2).transitions.add(new Transition(Arrays.asList(),states.size()-1));
                i+=2;
            }else{ 
                states.get(states.size()-2).transitions.add(new Transition(trans,states.size()-1));
                i++;
            }
        } 
        //final state
        if(states.size()>0){
            states.get(states.size()-1).isFinal=true;
        }
        return states;
    }
    
    //find all reachable states from the currentStateId using epsilon transtion
    public static Set<Integer> findEpslitionStates(List<State> automaton,int currentStateId){
        Set<Integer> epsilonStates = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        q.add(currentStateId);
        while(!q.isEmpty()){
            int stateId = q.poll();
            epsilonStates.add(stateId);
            for (Transition t : automaton.get(stateId).transitions){
                if (t.transitionCharacters.isEmpty()){
                    q.add(t.nextState);
                }
            }
        }
        return  epsilonStates;
    }

    class Transition {
        List<Character> transitionCharacters;
        int nextState;
        public Transition(List<Character> transitionCharacters,int nextState) {
            this.transitionCharacters = transitionCharacters;
            this.nextState= nextState;
        }
        public String toString() {   
            return "transChars =" + transitionCharacters  + " |nextState =" +nextState;
        }   
        }
    
    class State {
        int currentId;
        Boolean isFinal;
        List<Transition> transitions= new ArrayList<>();
        public State ( int currentId,boolean isFinal){
            this.isFinal=isFinal;
            this.currentId=currentId;
        }
    
        //-1 = dead state when applying this transition
        int consumeCharacter(char c){
                for (Transition t : transitions){
                    if (t.transitionCharacters.contains(c)){
                        return t.nextState;
                    }
                }
            return -1;
        }
        
        //return the set of next states when applying the c character
        public Set<Integer>  nexStates(List<State> automaton,char c){
            Set<Integer> nextStates=new HashSet<>();
            Set<Integer> epsilonStates = findEpslitionStates(automaton,this.currentId);
            //System.out.println("epsilon " + epsilonStates);
            for (Integer id : epsilonStates){
                int nexState = automaton.get(id).consumeCharacter(c);
                nextStates.add(nexState);
            }
            return nextStates;
        }
        
        
        public String toString(){
            String response="{";
            for (Transition t :  transitions ){
                response += "\n"  + t ;
            }
            response += "\n ,id  :"+currentId +" ,isFinal : " + isFinal +"}";
            return response;
        }
    }
    
    public static boolean isMatch(String s, String p) {
        List<State> aut = new Solution().buildAutomaton(p);
        //System.out.println(aut);
        Set<Integer> currentStates=new HashSet<>();
        currentStates.add(0);
        boolean deadState=false;
        
        for (int i = 0; i < s.length() && !deadState ; i++){
            char c = s.charAt(i);
            //System.out.print(currentStates + " -> " + c + " -> " );
            Set<Integer> nexStates = new HashSet<>();
            for (Integer currentState : currentStates){
                    Set<Integer> states = aut.get(currentState).nexStates(aut,c);
                nexStates.addAll(states);
            }
            
            if (nexStates.size()==1 && nexStates.contains(-1)){
                deadState=true;
            }else {
                nexStates.remove(-1);
                currentStates=nexStates;
            }
                //System.out.println(currentStates);
            
        }
        Set<Integer> finalStates = new HashSet();
        for (Integer st : currentStates) {
            Set<Integer> states = findEpslitionStates(aut,st);
            finalStates.addAll(states);
        }
        boolean reachFinalState = finalStates.stream().filter(st -> aut.get(st).isFinal).collect(Collectors.toList()).isEmpty()==false;
        
        return !deadState && reachFinalState;
    }

    public static void main(String[] args) {
        String s = "aaa";
        String p = "a*";
        System.out.println(isMatch(s, p));
    }
}
