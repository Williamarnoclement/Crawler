
# Web Crawler 

## The project
This repository is dedicated to my from scratch web search engine with its index on a MySQL database, a crawler written in JAVA and a ranker written in PHP (not included in this git version).
A demo version indexed ~4 700 pages.

## How it works
A recursive function is dedicated to crawl the web. For each new web page founded, several classes are instanciated. There are multiples checks to do before registering the new page into the index, like read the robot.txt file, count how many pages redirect on the current page, and much more.

Here is a short diagram to understand the crawler (written in french).

![how it works](https://raw.githubusercontent.com/Williamarnoclement/Crawler/master/schemas/wac.png)

## About me 
My name is William-Arno and I love make things ! Discover my code on [Github profile](https://github.com/Williamarnoclement) and on my [website](https://www.griffure.com).

I did an article (in french) about the process of making a search engine available on [my blog](https://www.griffure.com/comment-jai-fait-mon-moteur-de-recherche-web/).
