package oop.assignment.linkedlist;

public class LinkList<T> {
    private Element head;
    private Element ptr;

    public LinkList(){
        head = null;
        ptr = null;
    }

    public void add(T obj){
        if(head == null){
            head = new Element(obj);
            ptr = head;
        } else if (ptr.getObj() == null){
            ptr.setObj(obj);
        } else if (ptr.getNext() == null){
            ptr.setNext(new Element(obj));
        } else {
            ptr.setNext(new Element(obj, ptr.getNext()));
        }
    }

    public void next(){
        if(!ptr.hasNext()){
            throw new NoNextElementException();
        } else {
            ptr = ptr.getNext();
        }
    }

    public void pointToHead(){
        ptr = head;
    }

    public void remove(){
        if(!ptr.hasNext()){
            ptr.setObj(null);
        } else {
            ptr.setObj(ptr.getNext().getObj());
            ptr.setNext(ptr.getNext().getNext());
        }
    }

    public void remove(int index){
        Element tmpptr = ptr;
        pointToHead();
        for(int i = 0; i < index; i++){
            next();
        }
        remove();
        ptr = tmpptr;
    }

    public void remove(T obj){
        remove(search(obj));
    }

    public int search(T obj){
        Element tmpptr = ptr;
        pointToHead();
        for(int i = 0; i < size(); i ++){
            if(ptr.getObj().equals(obj)){
                return i;
            }
            next();
        }
        ptr = tmpptr;
        return -1;
    }

    public int size(){
        Element tmpptr = ptr;
        pointToHead();
        int size = 0;
        while(ptr.hasNext()){
            next();
            size++;
        }
        ptr = tmpptr;
        return size;
    }

    public T get(){
        return ptr.getObj();
    }

    public T get(int index){
        Element tmpptr = ptr;
        moveTo(index);
        T obj = get();
        ptr = tmpptr;
        return obj;
    }

    public void moveTo(int index){
        pointToHead();
        for(int i = 0; i < index; i++){
            next();
        }
    }

    private class Element{

        private T obj;
        private Element next;

        Element(T obj){
            this.obj = obj;
            this.next = null;
        }

        Element(T obj, Element next){
            this.obj = obj;
            this.next = next;
        }

        private T getObj() {
            return obj;
        }

        private void setObj(T obj) {
            this.obj = obj;
        }

        private Element getNext() {
            return next;
        }

        private void setNext(Element next) {
            this.next = next;
        }


        private boolean hasNext(){
            if(next != null) {
                return true;
            }
            return false;
        }

    }
}
