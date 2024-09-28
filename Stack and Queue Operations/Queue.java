

import java.util.NoSuchElementException;

public class Queue {
     
    private Node front; 
    private Node rear;
    
    private int size;
    
  
    private static class Node{
        
        int element;
        Node next;

        Node(int item, Node next)
        {
            this.element = item;
            this.next = next;
        }

        Node(int item)
        {
            this.element = item;
            this.next = null;
        }
        
    }
    
    
    public Queue()
    {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }
    
    // adds the element to the end of the Queue.
    public void offer(int element)
    {
        final Node r = rear;
        Node newNode = new Node(element);
        
        if(front == null)
        {
            front = newNode;
            rear = newNode;
        }
        else
        {
            r.next = newNode;
            rear = newNode;
        }
            
        this.size++;
    }
    
    // returns and removes the head of the Queue.
    public int poll()
    {
        if(front == null)
        {
            throw new NoSuchElementException();
        }
        
        final Node f = front;
        final int element = f.element;
        
        if(front.next == null)
        {
            front= null;
            rear = null;
        }
        else
        {
            front = f.next;
        }
        
        this.size--;
        return element;
    }
    
    // returns the head of the Queue, but does not remove.
    public int peek()
    {
        
        if(front == null)
        {
            throw new NoSuchElementException();
        }
        
        final Node f = front;
        final int element = f.element;
        
        return element;
    }
    
    
    // removes the numbers from the Queue that is greater than given k number.
    public boolean removeGreaterNumber(int k)
    {
        if(front == null)
        {
            return false;
        }
        
        if(front.next == null)
        {
          if(front.element > k)
          {
              poll();
          }
          return true;
        }
        
        Node current = front;
        
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
        
        if(front.element > k)
        {
            poll();
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
        
        for(Node x = front ; x != null ; x = x.next )
        {
            for( Node y = x.next; y != null ; y = y.next)
            {
                distance += Math.abs(x.element - y.element);
            }
        }
        
        return distance;
    }
    
    
    // reverses queue in length first k elements. it uses my Stack class to help reversing
    public void reverse(int k)
    {
        if(isEmpty())
        {
            return;
        }
        
        Stack reversedPartial = new Stack();
        
        int counter = 0;
        while(counter < k && !isEmpty())
        {
            reversedPartial.push(poll());
            counter++;
        }
        
        Queue restOfQueue = new Queue();
        while( !isEmpty())
        {
            restOfQueue.offer(poll());
        }
        
        while(!reversedPartial.isEmpty())
        {
            offer(reversedPartial.pop());
        }
        
        while(!restOfQueue.isEmpty())
        {
            offer(restOfQueue.poll());
        }
        
    }
    
    // used optimized bubble sort to sort elements.
    public void sort()
    {
        
        for(Node x = front ; x != null; x = x.next)
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
       
       for(Node x = front ; x != null; x = x.next)
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
                offer((int)(Math.random() * 51));
            }
        }
        else
        {
            int count = 0;
            while( count>k && !isEmpty())
            {
                poll();
                count--;
            }
        }
    }
    
    
    public int size()
    {
        return this.size;
    }
        
    public boolean isEmpty()
    {
        return front == null ;
    }
  
    // toString method is used to get elements in a single line.
    @Override
    public String toString()
    {
        String all = "";
        
        Node current = front;
        while(current != null)
        {
            all += current.element + " ";
            current = current.next;
        }
        
        return all.trim();
    }
     
}


        
     
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        