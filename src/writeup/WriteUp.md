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
Using arrays for FIFO worklists is perfect when we know a max size of the worklist, peek(i) becomes very easy
 and quick for arrays because its just a little math and accessing one index in the array which is O(1). 
 The problem with arrays is that they can't be resized, and it would take 0(n) time to copy every time we 
 need a bigger array. Other worklists come into play here with using some type of resizable structure such 
 as linked lists, in these worklists we no longer have access to any of the middle elements and thus is 
 would take 0(n) time to access a certain element, this is not optimal.
</pre><br>
-   As we've described it, a `TrieMap` seems like a general-purpose replacement for `HashMap` or `TreeMap`.  Why might we still want to use one
    of these other data structures instead?<pre>
The other data structures allow us to use other types of keys other than strings/bytes. HashMap and TreeMap allow 
us to use whatever we want as keys. We can also choose between HashMap/TreeMap depending on if we want out keys
to be sorted. 
</pre><br>
-   One of the applications of Tries is in solving Word Searches.  A "word search" is an n x m rectangle of letters.  The goal is to find all
    of the possible words (horizontal, vertical, diagonal, etc.).  In Boggle, a similar game, any consecutive chain of letters
    are allowed.  Explain (in very high-level pseudo-code) how you might solve this problem with a TrieSet or a TrieMap.  Make sure to detail
    how a similar solution that uses a HashSet/HashMap instead would be different and why using a Trie might make the solution better.<pre>
With a TrieSet or a TrieMap we can store the dictionary in a very efficient way, when checking each possible row/col/diag
we can check the dictionary for each prefix, if a prefix isn't in the dictionary then we know the rest of the row/col
contains no words in the dictionary and can move onto the next line (i.e. if we have a line "tttttt", after we check "t",
when we go to check "tt", the prefix won't be contained in the dictionary and we can skip checking for "ttt", "tttt", "tttt", the same goes for boggle except you have to use a recursive method to create the lines by making a line for each 
of the 8 surrounding squares.
This is especially useful because the majority of possible words that you would have to check in these games are not words in the dictionary, therefore eliminating a lot of the work needed to be done, if you chose to use a HashMap or 
TreeMap you would have to exhaust all possible lines in order to find all the possible words.

</pre><br>
-   One of the classes in the main package is called Zip.  This class uses your PriorityQueue to do Huffman coding, your FIFOQueue as a buffer,
    your stack to calculate the keyset of a trie (using recursive backtracking), and your SuffixTrie to do LZ77Compression.  Find some text file
    (a free book from https://www.gutenberg.org/ or even the HTML of some website) and use Zip.java to zip it into a zip file.  Then, use a 
    standard zip utility on your machine (Finder on OS X, zip on Linux, WinZip or the like on Windows) to UNZIP your file.  Check that you got back
    the original.  Congratulations!  Your program correctly implements the same compression algorithm you have been using for years!  Discuss in a
    sentence or two how good the compression was and why you think it was good or bad.<pre>
The zip program worked correctly as far as being correct, when unzipping the zipped file, it 
was an exactly copy of what we zipped.  Measuring the time was harder with many outside 
variables affecting it, but keeping them as constant as possible our zip still ran a decent 
amount slower than the the 7zip program we used.  Also, the zip file from 7zip was around 
half the size of the fileour zip program ran.  Reading ahead to the next question, this 
could be changed by changing the BUFFER_LENGTH to a larger value.  Our zip program is 
most likely slower due to being made so it can implement our structures rather than being 
made to optimize the zipping.  Programs like 7zip have had years to optimize how they run 
and probably have structures built specificically for huffman encoding and/or use other 
algorithms that zip files with different distributions of characters better. 
</pre><br>
-   Now that you've played with Zip, we want you to do an **experiment** with Zip.  Notice that there is a constant called `BUFFER_LENGTH` in `Zip.java`.
    Higher values of this constant makes the compression algorithm that Zip uses use more memory and consequently more time.  The "compression ratio"
    of a file is the uncompressed size divided by the compressed size.  Compare time, type of input file, and compression ratio by running
    your code on various inputs.  We would like an in-depth analysis.  You should try at least one "book-like" file, at least one "website-like" file,
    and some other input of your choice.  We expect you to draw meaningful conclusions and possibly have graphs that convince us of your conclusions. 
    Say something about WHY you think you may have gotten the results you did.
    This single question is worth almost as much as the implementation of `ArrayStack`; so, please take it seriously.  If you spend less than 20 minutes
    on this question, there is no conceivable way that you answered this question in the way we were intending.<pre>
First we tested the same text file across many differ buffer lengths between 10 and 2000.  We used 
Date().getTime() differences to compare zip times, the times aren't useful for comparing against
any other programs or systems as the time it takes is completely dependent on your system but we were
able to get graph with pretty strong correlation around two different lines.  We had to take a lot
 more data points around 200 - 400 buffer size because around here the slope of the lines changed 
 dramatically.  The first line of best fit went from about 10 - 215 where it leaped to a different
  line of best fit whose time/buffer_size was about 150% of the first line.  From what we know about
   buffer size this probably has to do with having to access different/further sections of memory 
   and is dependent on the system. Both lines were linear, probably due to the worst big-O of the 
   structures used was 0(n) which is linear. The html for this gitlab page was much smaller than 
   the size of the book, so we copied and pasted the html until it was a comparable size to the 
   book.  The difference between times zipping the book and html were very similiar when holding 
   the size constant.  One thing we noticed was that the size of the zipped html was much smaller 
   (about 2/3s) of the size of the zipped book.  The is likely to be due to html containing a lot 
   of the same patterns (div, content, etc) and the same characters (<, >, ", =) repeated a lot 
   making the huffman encoding much more efficient.
</pre><br>

#### Above and Beyond ####
-   Did you do any Above and Beyond?  Describe exactly what you
    implemented.<pre>
**TODO**: Answer this question
</pre><br>
