
import java.util.NoSuchElementException;


public class Stack {
    
    private Node top; 
    
    private int size;
    
    
    
    private static class Node{
        
        int element;
        Node next;
        
        Node(int element, Node next)
        {
            this.element = element;
            this.next = next;
        }
        
        Node(int element)
        {
            this.element = element;
            this.next = null;
        }
        
    }
    
    
    public Stack()
    {
        this.top = null;
        this.size = 0;
    }
    
    // adds the element to the top of the Stack. 
    public void push(int element)
    {
        final Node t = top;
        final Node newNode = new Node(element, t);
        
        top = newNode;     
        size++;
    }
    
    // returns the top of the Stack, but does not remove.
    public int peek()
    {
        if(this.top == null)
        {
            throw new NoSuchElementException();
        }
        
        return this.top.element;
    }
    
    // returns and removes the top of the Stack.
    public int pop()
    {
        if(this.top == null)
        {
            throw new NoSuchElementException();
        }
        
        final Node t = top;
        final int element = t.element;
        
        this.top = t.next;
        
        size--;
        return element;
    }
    
   
     // removes the numbers from the Stack that is greater than given k number.
    public boolean removeGreaterNumber(int k)
    {
        if(top == null)
        {
            return false;
        }
        
        if(top.next == null)
        {
          if(top.element > k)
          {
              pop();
          }
          return true;
        }
        
        Node current = top;
        
        while(current.next != null)
        {
            if(current.next.element > k)
            {
                final Node deleted = current.next;
                current.next = deleted.next;
            }
            else
            {
                current = current.next;
            }
        }
        
        if(top.element > k)
        {
            pop();
        }
        return true;
    }
    
     // calculates distance.
    public int calculateDistance()
    {
        
        if(isEmpty())
        {
            return 0;
        }
        
        int distance = 0;
        
        for(Node x = top ; x != null ; x = x.next )
        {
            for( Node y = x.next; y != null ; y = y.next)
            {
                distance += Math.abs(x.element - y.element);
            }
        }
        
        return distance;
    }
    
     // reverses stack in length first k elements. it uses my Queue class to help reversing
    public void reverse(int k)
    {
        if(isEmpty())
        {
            return;
        }
        
        Queue reversedPartial = new Queue();
        
        int counter = 0;
        
        while(counter < k && !isEmpty())
        {
            reversedPartial.offer(pop());
            counter++;
        }
        
        
        while(!reversedPartial.isEmpty())
        {
            push(reversedPartial.poll());
        }
        
    }
    
    // used optimized bubble sort to sort elements.
    public void sort()
    {
        
        for(Node x = top ; x != null; x = x.next)
        {
            Node minNode = x;
            for(Node y = x.next ; y != null; y = y.next)
            {
               if(minNode.element > y.element)
               {
                   minNode = y;
               }
               
            }
            if(minNode != x)
            {
                int temp = x.element;
                x.element = minNode.element;
                minNode.element = temp;
            }
        }
        
    }
    
     // calculates how many distinct(unique) elements are there
    public int distinctElementCounter()
    {
       int distinctCounter = 0;
       
       for(Node x = top ; x != null; x = x.next)
        {
            int currentElementCounter = 0;
            int currentElement = x.element;
            
            for(Node y = x ; y != null; y = y.next)
            {
               if(currentElement == y.element)
               {
                   currentElementCounter++;
               }
               
            }
            if(currentElementCounter == 1)
            {
                distinctCounter++;
            }
        }
       
       return distinctCounter;
    }
    
     // if k > 0 it adds elements that is choosen randomly(between 0 and 50)(includes boundaries) k times. otherwise, removes elements k times.
   public void addOrRemove(int k)
   {
       if(k > 0)
       {
           for(int i =0 ; i<k ; i++)
           {
               
               push((int)(Math.random() * 51));
           }
       }
       else
       {
           int count = 0;
           while( count>k && !isEmpty())
           {
               pop();
               count--;
           }
        }
    }
    
   // toString method is used to get elements in a single line.
    @Override
    public String toString()
    {
        String all = "";
        
        Node current = top;
        while(current != null)
        {
            all += current.element + " ";
            current = current.next;
        }
        
        return all.trim();
    }
    
    public boolean isEmpty()
    {
        return top == null;
    }
    
    public int size()
    {
        return size;
    }
}
