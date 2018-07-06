# Project 1 (Zip) Write-Up #
--------

#### How Was Your Partnership? ####
-   Did both partners do an equal amount of work?  If not, why not?
    If so, what did each person do? What happened?<pre>
Each of us did about the same amount of work. When we coded together, 
Jory did the typing while we both came up with the ideas. We also each did
a little bit of work on our own when we could not meet up. For example, Ryan
did a lot of minFourHeap alone and Jory finished up hashTrieMap alone.
</pre><br>

-----

#### Project Enjoyment ####
-   What was your favorite part of the project?  What was your least
    favorite part of the project?<pre>
Our favorite part of the project was implementing the Heaps and Tries because
of how the complex math worked out neatly in the end. 
Our least favorite part was dealing with the wrap around of the circularArray
because it took us a while to iron out the little small errors with +1/-1/etc.
</pre><br>

-   Did you enjoy the project?  Why or why not?<pre>
Overall we enjoyed the project because it was out first time working together
and the material was interesting and easy to work together on.
</pre><br>

-----

#### WorkLists, Tries, and Zip ####
-   The ADT for a WorkList explicitly forbids access to the middle elements.  However, the FixedSizeFIFOWorkList has a peek(i) method
    which allows you to do exactly that.  Why is this an acceptable addition to the WorkList ADT in this particular case but not in general?  
    In other words, what about fixed size FIFO worklists makes peek(i) make sense? Why does peek(i) NOT make sense in other worklist implementations?<pre>
Using arrays for FIFO worklists is perfect when we know a max size of the worklist, peek(i) becomes very easy and quick for arrays because its just a little math and accessing one index in the array which is O(1). The problem with arrays is that they can't be resized, and it would take 0(n) time to copy every time we need a bigger array. Other worklists come into play here with using some type of resizable structure such as linked lists, in these worklists we no longer have access to any of the middle elements and thus is would take 0(n) time to access a certain element, this is not optimal.

In other worklists, the resizing of the worklist can make the array grow very large, so it may take a long time to find the element. In a fixed length list, the length of the list is controlled, so we know it will not grow
out of control.
</pre><br>
-   As we've described it, a `TrieMap` seems like a general-purpose replacement for `HashMap` or `TreeMap`.  Why might we still want to use one
    of these other data structures instead?<pre>
The other data structures allow us to use other things than strings/bytes as keys. HashMap and TreeMap allow 
us to use whatever we want as keys. We can also choose between HashMap/TreeMap depending on if we want out keys
to be sorted. 
</pre><br>
-   One of the applications of Tries is in solving Word Searches.  A "word search" is an n x m rectangle of letters.  The goal is to find all
    of the possible words (horizontal, vertical, diagonal, etc.).  In Boggle, a similar game, any consecutive chain of letters
    are allowed.  Explain (in very high-level pseudo-code) how you might solve this problem with a TrieSet or a TrieMap.  Make sure to detail
    how a similar solution that uses a HashSet/HashMap instead would be different and why using a Trie might make the solution better.<pre>

Begin with an empty Trie. If the word is a valid word then insert the word to the Trie by building off a
prefix or adding an entire new key. It's value would be the string of the word. 
A HashMap or HashSet would not allow players to build off of other words.

</pre><br>
-   One of the classes in the main package is called Zip.  This class uses your PriorityQueue to do Huffman coding, your FIFOQueue as a buffer,
    your stack to calculate the keyset of a trie (using recursive backtracking), and your SuffixTrie to do LZ77Compression.  Find some text file
    (a free book from https://www.gutenberg.org/ or even the HTML of some website) and use Zip.java to zip it into a zip file.  Then, use a 
    standard zip utility on your machine (Finder on OS X, zip on Linux, WinZip or the like on Windows) to UNZIP your file.  Check that you got back
    the original.  Congratulations!  Your program correctly implements the same compression algorithm you have been using for years!  Discuss in a
    sentence or two how good the compression was and why you think it was good or bad.<pre>
The compression seemed just as fast as I am use to seeing zip files compress. I think it worked well because
we made sure all our worklist data structures are efficient and run in the correct runtimes.
</pre><br>
-   Now that you've played with Zip, we want you to do an **experiment** with Zip.  Notice that there is a constant called `BUFFER_LENGTH` in `Zip.java`.
    Higher values of this constant makes the compression algorithm that Zip uses use more memory and consequently more time.  The "compression ratio"
    of a file is the uncompressed size divided by the compressed size.  Compare time, type of input file, and compression ratio by running
    your code on various inputs.  We would like an in-depth analysis.  You should try at least one "book-like" file, at least one "website-like" file,
    and some other input of your choice.  We expect you to draw meaningful conclusions and possibly have graphs that convince us of your conclusions. 
    Say something about WHY you think you may have gotten the results you did.
    This single question is worth almost as much as the implementation of `ArrayStack`; so, please take it seriously.  If you spend less than 20 minutes
    on this question, there is no conceivable way that you answered this question in the way we were intending.<pre>
The first file I tested was a "book-like" file. I tested buffer lengths from 10-1000 in increments. My data 
was (in the form of (Buffer-Length, time(s), size(kb)): (10,2.3, 79), (50, 3.0, 78), (200, 8.0, 78), (400, 40.5, 73), (600, 78, 72), (800, 104, 72), (1000, 138, 71). After plotting these points on a white board, I noticed slight linear increases in time when 
the Buffer Length was between 10 and 200. The biggest increase in time was when I changed it from 200 to 400. After that, the time seemed to increase linearly again. I think that jump in time could be caused by some of 
the slower operations of our data structures such as resizing the worklist. If I continued to test higher Buffer Lengths, I think I would see another jump in time once the lists resize again. That jump in time is also when the the biggest jump in compression was. I noticed similar trends when I tested another book and HTML code.
</pre><br>

#### Above and Beyond ####
-   Did you do any Above and Beyond?  Describe exactly what you
    implemented.<pre>
**TODO**: Answer this question
</pre><br>
