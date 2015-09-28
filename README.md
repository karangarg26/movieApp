# movieApp

API based Movie Listing

Problem statement:
To show list of movies ordered by its rating on listview with all the below details:

1. Movie poster
2. Movie name
3. Movie released year
4. Movie rating.

All the above details can be fetched from the below well documented API(prefer JSON API):
https://yts.to/api#list_movies

Implementation prerequisites:
1. Min SDK should be 14
2. Should be using Graddle
3. Phone supported
4. Should be done using RecyclerView(support library)
5. Initially 30 items only fetched for first page, other items should be loaded on demand when scrolling
down and reaching the end of list.
6. Images should be loaded on demand(on scroll) and should be cached.
7. A detailed view should be shown based on the selected list.
