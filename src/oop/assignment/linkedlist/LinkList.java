package oop.assignment.linkedlist;

import oop.assignment.linkedlist.exceptions.NoNextElementException;
import oop.assignment.linkedlist.exceptions.NullHeadException;

/**
 * My implementation of the linked list data structure.
 *
 * @param <T> the type of object to be stored in the LinkList
 */
public class LinkList<T> {
    /**
     * the first element of the linked list
     */
    private Element head;
    /**
     * a pointer to the current element being used.
     */
    private Element ptr;

    /**
     * Initialises the attributes to null, so that they can be checked and used.
     */
    public LinkList(){
        head = null;
        ptr = null;
    }

    /**
     * Inserts the object as the element after the ptr in the list, even if it is not pointing at the end.
     *
     * @param obj the object to add to the list at this position
     */
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

    /**
     * Adds the object to the end of the list.
     *
     * @param obj the object to append to the list
     */
    public void append(T obj){
        if(isEmpty()){
            add(obj);
        } else {
            Element tmpptr = ptr;
            moveTo(size() - 1);
            add(obj);
            ptr = tmpptr;
        }
    }

    /**
     * If the list is empty or there is no next element,
     * it throws a NullHeadException or a NoNextElementException respectively.
     * Otherwise, it just shifts the pointer to the next element in the list.
     */
    public void next(){
        if(isEmpty()){
            throw new NullHeadException();
        }

        if(!ptr.hasNext()){
            throw new NoNextElementException();
        } else {
            ptr = ptr.getNext();
        }
    }

    /**
     * Sets ptr to point to head.
     */
    public void pointToHead(){
        ptr = head;
    }

    /**
     * Removes the element currently referenced by ptr,
     * shifting the rest of the list if there are any more elements.
     */
    public void remove(){
        if(isEmpty()){
            throw new NullHeadException();
        }

        if(!ptr.hasNext()){
            ptr.setObj(null);
        } else {
            ptr.setObj(ptr.getNext().getObj());
            ptr.setNext(ptr.getNext().getNext());
        }
    }

    /**
     * Removes the element at the desired index.
     *
     * @param index the index of the object to remove
     */
    public void remove(int index){
        Element tmpptr = ptr;
        moveTo(index);
        remove();
        ptr = tmpptr;
    }

    /**
     * Searches the list for the object and removes it.
     *
     * @param obj the object to remove from the list
     */
    public void remove(T obj){
        remove(search(obj));
    }

    /**
     * Trawls the list in search of the object at returns its index or -1 if it is not found.
     *
     * @param obj the object to search for
     * @return the index of the object, or -1 if it is not in the list
     */
    public int search(T obj){
        if(isEmpty()){
            throw new NullHeadException();
        }

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

    /**
     * Calculates and returns the size of the list.
     *
     * @return the size of the list
     */
    public int size(){
        if(isEmpty()) {
            return 0;
        }

        Element tmpptr = ptr;
        pointToHead();
        int size = 1;

        while(ptr.hasNext() && ptr.getNext().getObj() != null){
            next();
            size++;
        }
        ptr = tmpptr;
        return size;
    }

    /**
     * @return the object at the current element
     */
    public T get(){
        if(isEmpty()){
            throw new NullHeadException();
        }
        return ptr.getObj();
    }

    /**
     * @param index the index of the desired object
     * @return the object at the passed index
     */
    public T get(int index){
        Element tmpptr = ptr;
        moveTo(index);
        T obj = get();
        ptr = tmpptr;
        return obj;
    }

    /**
     * Moves the pointer to the desired position by pointing to the head and moving one by one.
     *
     * @param index the index of the desired position
     */
    public void moveTo(int index){
        if(isEmpty()){
            throw new NullHeadException();
        }

        pointToHead();
        for(int i = 0; i < index; i++){
            next();
        }
    }

    /**
     * @return whether the head is null or the head is empty
     */
    public boolean isEmpty(){
        return head == null || head.getObj() == null;
    }

    /**
     * The private inner class which describes a link in the linked list,
     * it holds the object stored and a reference to the next element in the list.
     */
    private class Element{

        /**
         * the object stored in this element
         */
        private T obj;
        /**
         * the next element in the list
         */
        private Element next;

        /**
         * Initialises the element without a next element.
         *
         * @param obj the object to be stored in this element
         */
        Element(T obj){
            this.obj = obj;
            this.next = null;
        }

        /**
         * Initialises the element with a next element.
         *
         * @param obj the object to be stored in this element
         * @param next the reference to the object after this one in the list
         */
        Element(T obj, Element next){
            this.obj = obj;
            this.next = next;
        }

        /**
         * @return the object stored in this element
         */
        private T getObj() {
            return obj;
        }

        /**
         * @param obj the object to replace the current object
         */
        private void setObj(T obj) {
            this.obj = obj;
        }

        /**
         * @return the next element in the list, which might be null
         */
        private Element getNext() {
            return next;
        }

        /**
         * Sets the passed element to be the next element in the list.
         *
         * @param next a reference to an element object
         */
        private void setNext(Element next) {
            this.next = next;
        }

        /**
         * @return whether the next element exists
         */
        private boolean hasNext(){
            return next != null;
        }

    }
}
