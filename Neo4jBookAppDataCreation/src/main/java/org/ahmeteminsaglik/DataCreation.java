package org.ahmeteminsaglik;

import java.util.*;

public class DataCreation {

    private StringBuilder queryText = new StringBuilder("");
    private final int userNumber = 30;
    static String[] maleName = {"James", "Robert", "John", "Michael", "David", "William", "Richard", "Joseph", "Thomas", "Charles", "Christopher", "Daniel", "Matthew", "Anthony", "Mark", "Donald", "Steven", "Paul", "Andrew", "Joshua"};
    static String[] femaleName = {"Mary", "Patricia", "Jennifer", "Linda", "Elizabeth", "Barbara", "Susan", "Jessica", "Sarah", "Karen", "Lisa", "Nancy", "Betty", "Margaret", "Sandra", "Ashley", "Kimberly", "Emily", "Donna", "Michelle", "Carol", "Amanda"};
    static String[] bookName = {"Don Quixote Miguel de Cervantes", "Alice's Adventures in Wonderland Lewis Carroll", "The Adventures of Huckleberry Finn Mark Twain", "The Adventures of Tom Sawyer Mark Twain", "Treasure Island Robert Louis Stevenson", "Pride and Prejudice Jane Austen", "Wuthering Heights Emily Brontë", "Jane Eyre Charlotte Brontë", "Moby Dick Herman Melville", "The Scarlet Letter Nathaniel Hawthorne", "Gulliver's Travels Jonathan Swift", "The Pilgrim's Progress John Bunyan", "A Christmas Carol Charles Dickens", "David Copperfield Charles Dickens", "A Tale of Two Cities Charles Dickens", "Little Women Louisa May Alcott", "Great Expectations Charles Dickens", "The Hobbit, or, There and Back Again J.R.R Tolkien", "Frankenstein, or, the Modern Prometheus Mary Shelley", "Oliver Twist Charles Dickens", "Uncle Tom's Cabin Harriet Beecher Stowe", "Crime and Punishment Fyodor Dostoyevsky", "Madame Bovary: Patterns of Provincial life Gustave Flaubert", "The Return of the King J.R.R Tolkien", "Dracula Bram Stoker", "The Three Musketeers Alexandre Dumas", "Brave New World Aldous Huxley", "War and Peace Leo Tolstoy", "To Kill a Mockingbird Harper Lee", "The Wizard of Oz L Frank Baum", "Les Misérables Victor Hugo", "The Secret Garden Frances Hodgson Burnett", "Animal Farm George Orwell", "The Great Gatsby F Scott Fitzgerald", "The Little Prince Antoine de Saint-Exupéry", "The Call of the Wild Jack London", ", Leagues Under the Sea Jules Verne", "Anna Karenina Leo Tolstoy", "The Wind in the Willows Kenneth Grahame", "The Picture of Dorian Gray Oscar Wilde", "The Grapes of Wrath John Steinbeck", "Sense and Sensibility Jane Austen", "The Last of the Mohicans James Fenimore Cooper", "Tess of the d'Urbervilles Thomas Hardy", "Harry Potter and the Sorcerer's Stone J.K Rowling", "Heidi Johanna Spyri", "Ulysses James Joyce", "The Complete Sherlock Holmes Arthur Conan Doyle", "The Count of Monte Cristo Alexandre Dumas", "The Old Man and the Sea Ernest Hemingway", "The Lion, the Witch, and the Wardrobe C.S Lewis", "The Hunchback of Notre Dame Victor Hugo", "Pinocchio Carlo Collodi", "One Hundred Years of Solitude Gabriel García Márquez", "Ivanhoe Walter Scott", "The Red Badge of Courage Stephen Crane", "Anne of Green Gables L.M Montgomery", "Black Beauty Anna Sewell", "Peter Pan J.M Barrie", "A Farewell to Arms Ernest Hemingway", "The House of the Seven Gables Nathaniel Hawthorne", "Lord of the Flies William Golding", "The Prince and the Pauper Mark Twain", "A Portrait of the Artist as a Young Man James Joyce", "Lord Jim Joseph Conrad", "Harry Potter and the Chamber of Secrets J.K Rowling", "The Red & the Black Stendhal", "The Stranger Albert Camus", "The Trial Franz Kafka", "Lady Chatterley's Lover D.H Lawrence", "Kidnapped: The Adventures of David Balfour Robert Louis Stevenson", "The Catcher in the Rye J.D Salinger", "Fahrenheit  Ray Bradbury", "A Journey to the Center of the Earth Jules Verne", "Vanity Fair William Makepeace Thackeray", "All Quiet on the Western Front Erich Maria Remarque", "Gone with the Wind Margaret Mitchell", "My Ántonia Willa Cather", "Of Mice and Men John Steinbeck", "The Vicar of Wakefield Oliver Goldsmith", "A Connecticut Yankee in King Arthur's Court Mark Twain", "White Fang Jack London", "Fathers and Sons Ivan Sergeevich Turgenev", "Doctor Zhivago Boris Leonidovich Pasternak", "The Decameron Giovanni Boccaccio", "Nineteen Eighty-Four George Orwell", "The Jungle Upton Sinclair", "The Da Vinci Code Dan Brown", "Persuasion Jane Austen", "Mansfield Park Jane Austen", "Candide Voltaire", "For Whom the Bell Tolls Ernest Hemingway", "Far from the Madding Crowd Thomas Hardy", "The Fellowship of the Ring J.R.R Tolkien", "The Return of the Native Thomas Hardy", "Sons and Lovers D.H Lawrence", "Charlotte's Web E.B White", "The Swiss Family Robinson Johann David Wyss", "Bleak House Charles Dickens", "Père Goriot Honoré de Balzac", "Utopia Thomas More", "The History of Tom Jones, a Foundling Henry Fielding", "Harry Potter and the Prisoner of Azkaban J.K Rowling", "Kim Rudyard Kipling", "The Sound and the Fury William Faulkner", "Harry Potter and the Goblet of Fire J.K Rowling", "The Mill on the Floss George Eliot", "A Wrinkle in Time Madeleine L'Engle", "The Hound of the Baskervilles Arthur Conan Doyle", "The Two Towers J.R.R Tolkien", "The War of the Worlds H.G Wells", "Middlemarch George Eliot", "The Age of Innocence Edith Wharton", "The Color Purple Alice Walker", "Northanger Abbey Jane Austen", "East of Eden John Steinbeck", "On the Road Jack Kerouac", "Catch- Joseph Heller", "Around the World in Eighty Days Jules Verne", "Hard Times Charles Dickens", "Beloved Toni Morrison", "Mrs Dalloway Virginia Woolf", "To the Lighthouse Virginia Woolf", "The Magician's Nephew C.S Lewis", "Harry Potter and the Order of the Phoenix J.K Rowling", "The Sun Also Rises Ernest Hemingway", "The Good Earth Pearl S Buck", "Silas Marner George Eliot", "Love in the Time of Cholera Gabriel García Márquez", "Rebecca Daphne Du Maurier", "Jude the Obscure Thomas Hardy", "Twilight Stephenie Meyer", "A Passage to India E.M Forster", "The Plague Albert Camus", "Nicholas Nickleby Charles Dickens", "The Pearl John Steinbeck", "Ethan Frome Edith Wharton", "The Tale of Genji Murasaki Shikibu", "The Giver Lois Lowry", "The Alchemist Paulo Coelho", "The Strange Case of Dr Jekyll and Mr Hyde Robert Louis Stevenson", "Robinson Crusoe Daniel Defoe", "Tender is the Night F Scott Fitzgerald", "The Idiot Fyodor Dostoyevsky", "Hatchet Gary Paulsen", "The Kite Runner Khaled Hosseini", "One Flew Over the Cuckoo's Nest Ken Kesey", "The Portrait of a Lady Henry James", "The Outsiders S.E Hinton", "Ben-Hur Lew Wallace", "The Mayor of Casterbridge Thomas Hardy", "Cry, The Beloved Country Alan Paton", "The Last Battle C.S Lewis", "Captains Courageous Rudyard Kipling", "The Castle Franz Kafka", "The Metamorphosis Franz Kafka", "The Magic Mountain (Der Zauberberg) Thomas Mann", "James and the Giant Peach Roald Dahl", "The Horse and His Boy C.S Lewis", "Angels & Demons Dan Brown", "The Voyage of the Dawn Treader C.S Lewis", "The Bell Jar Sylvia Plath", "Women in Love D.H Lawrence", "The Yearling Marjorie Kinnan Rawlings", "O Pioneers! Willa Cather", "The Handmaid's Tale Margaret Atwood", "The Moonstone Wilkie Collins", "The Old Curiosity Shop Charles Dickens", "Little Dorrit Charles Dickens", "Prince Caspian: The Return to Narnia C.S Lewis", "Sister Carrie Theodore Dreiser", "The Silver Chair C.S Lewis", "The Hunger Games Suzanne Collins", "This Side of Paradise F Scott Fitzgerald", "Eugénie Grandet Honoré de Balzac", "Of Human Bondage W Somerset Maugham", "Dream of the Red Chamber Cao Xueqin", "Life of Pi Yann Martel", "Harry Potter and the Deathly Hallows J.K Rowling", "Invisible Man Ralph Ellison", "Steppenwolf Hermann Hesse", "The Sorrows of Young Werther Johann Wolfgang von Goethe", "Bridge to Terabithia Katherine Paterson", "The Invisible Man H.G Wells", "Holes Louis Sachar", "Siddhartha Hermann Hesse", "A Tree Grows in Brooklyn Betty Smith", "Through the Looking-Glass, and What Alice Found There Lewis Carroll", "In Cold Blood Truman Capote", "The House of the Spirits Isabel Allende", "Adam Bede George Eliot", "The Betrothed Alessandro Manzoni", "The Book Thief Markus Zusak", "Their Eyes Were Watching God Zora Neale Hurston", "One Day in the Life of Ivan Denisovich Aleksandr Isaevich Solzhenitsyn", "The Sea Wolf Jack London", "Catching Fire Suzanne Collins", "Roll of Thunder, Hear My Cry Mildred D Taylor", "Death Comes for the Archbishop Willa Cather", "The House of Mirth Edith Wharton", "Light in August William Faulkner", "The Pickwick Papers Charles Dickens", "Remembrance of Things Past Marcel Proust", "Barchester Towers and the Warden Anthony Trollope", "The Bridge of San Luis Rey Thornton Wilder", "The Help Kathryn Stockett", "Murder on the Orient Express Agatha Christie", "The Lovely Bones Alice Sebold", "The Appeal John Grisham", "Dombey And Son Charles Dickens", "Slaughterhouse-Five Kurt Vonnegut", "An American Tragedy Theodore Dreiser", "The Bluest Eye Toni Morrison", "Little House In the Big Woods Laura Ingalls Wilder", "Pippi Longstocking Astrid Lindgren", "Germinal Émile Zola", "The Heart Is a Lonely Hunter Carson McCullers", "The Woman In White Wilkie Collins", "Absalom, Absalom! William Faulkner", "A Painted House John Grisham", "The Girl With the Dragon Tattoo Stieg Larsson", "A Room With a View E.M Forster", "Watership Down Richard Adams", "Memoirs of a Geisha Arthur Golden", "Our Mutual Friend Charles Dickens", "Babbitt Sinclair Lewis", "The Red Pony John Steinbeck", "All the King's Men Robert Penn Warren", "Things Fall Apart Chinua Achebe", "Lorna Doone R.D Blackmore", "Johnny Tremain Esther Forbes", "Anne of Avonlea L.M Montgomery", "Tuck Everlasting Natalie Babbitt", "The BFG Roald Dahl", "Cannery Row John Steinbeck", "The Joy Luck Club Amy Tan", "The Silmarillion J.R.R Tolkien", "Roots Alex Haley", "Little House on the Prairie Laura Ingalls Wilder", "Native Son Richard Wright", "Stuart Little E.B White", "Cross Fire James Patterson", "The Power and the Glory Graham Greene", "A Clockwork Orange Anthony Burgess", "The Phantom of the Opera Gaston Leroux", "The Martian Chronicles Ray Bradbury", "The Road Cormac McCarthy", "The Way of All Flesh Samuel Butler", "Diary of a Wimpy Kid: The Long Haul Jeff Kinney", "Villette Charlotte Brontë", "The Curious Incident of the Dog In the Night-Time Mark Haddon", "The Mysterious Island Jules Verne", "Song of Solomon Toni Morrison", "Nana Émile Zola", "Quo Vadis Henryk Sienkiewicz", "DataCreation Street Sinclair Lewis", "Matilda Roald Dahl", "Lolita Vladimir Vladimirovich Nabokov", "Paper Towns John Green", "Sounder William H Armstrong", "Are You There God? It's Me, Margaret Judy Blume", "The Notebook Nicholas Sparks", "From the Mixed-Up Files of Mrs Basil E Frankweiler E.L Konigsburg", "Atlas Shrugged Ayn Rand", "The Fountainhead Ayn Rand", "Number the Stars Lois Lowry", "The Firm John Grisham", "Swann's Way Marcel Proust", "Ender's Game Orson Scott Card", "The Name of the Rose Umberto Eco", "A Time to Kill John Grisham", "Water for Elephants Sara Gruen", "The Time Machine H.G Wells", "Eragon Christopher Paolini", "The Hitchhiker's Guide to the Galaxy Douglas Adams", "Buddenbrooks Thomas Mann", "A Thousand Splendid Suns Khaled Hosseini", "The Witch of Blackbird Pond Elizabeth George Speare", "And Then There Were None Agatha Christie", "A Separate Peace John Knowles", "Breaking Dawn Stephenie Meyer", "As I Lay Dying William Faulkner", "The Girl Who Played With Fire Stieg Larsson", "Where the Red Fern Grows Wilson Rawls", "Le Morte D'Arthur Thomas Malory", "Mockingjay Suzanne Collins", "The Pillars of the Earth Ken Follett", "Persian Letters Montesquieu", "The Client John Grisham", "Sula Toni Morrison", "Tales of a Fourth Grade Nothing Judy Blume", "The Merry Adventures of Robin Hood of Great Renown In Nottinghamshire Howard Pyle", "Tortilla Flat John Steinbeck", "Look Homeward, Angel Thomas Wolfe", "The Mystery of Edwin Drood Charles Dickens", "Brideshead Revisited Evelyn Waugh", "The Pelican Brief John Grisham", "Atonement Ian McEwan", "Washington Square Henry James", "Like Water for Chocolate Laura Esquivel", "The Golden Compass Philip Pullman", "The Secret Life of Bees Sue Monk Kidd", "The Fault In Our Stars John Green", "Nostromo Joseph Conrad", "Finnegans Wake James Joyce", "The Brethren John Grisham", "Coraline Neil Gaiman", "Heart of Darkness Joseph Conrad", "On the Banks of Plum Creek Laura Ingalls Wilder", "Rebecca of Sunnybrook Farm Kate Douglas Smith Wiggin", "The Ambassadors Henry James", "The Secret Agent Joseph Conrad", "The House on Mango Street Sandra Cisneros", "Go Tell It on the Mountain James Baldwin", "The Testament John Grisham", "The Clan of the Cave Bear Jean M Auel", "Cranford Elizabeth Cleghorn Gaskell", "Because of Winn-Dixie Kate DiCamillo", "My Side of the Mountain Jean Craighead George", "The Runaway Jury John Grisham", "The Mouse and the Motorcycle Beverly Cleary", "The Lost Symbol Dan Brown", "The Forsyte Saga John Galsworthy", "Gone Girl Gillian Flynn", "The Lightning Thief Rick Riordan", "The Last Days of Pompeii Edward Bulwer Lytton", "The Reader Bernhard Schlink", "Caddie Woodlawn Carol Ryrie Brink", "The Tale of Despereaux Kate DiCamillo", "The Girl Who Kicked the Hornet's Nest Stieg Larsson", "Dear Mr Henshaw Beverly Cleary", "The Killer Angels Michael Shaara", "Chronicle of a Death Foretold Gabriel García Márquez", "The Five People You Meet In Heaven Mitch Albom", "The Master and Margarita Mikhail Bulgakov", "Winesburg, Ohio Sherwood Anderson", "Is for Peril Sue Grafton", "My Sister's Keeper Jodi Picoult", "Barnaby Rudge Charles Dickens", "Howards End E.M Forster", "The Broker John Grisham", "The Camel Club David Baldacci", "The Rainbow D.H Lawrence", "The Man In the Iron Mask Alexandre Dumas", "Mary Poppins P.L Travers", "Artemis Fowl Eoin Colfer", "Dear John Nicholas Sparks", "Cold Mountain Charles Frazier", "Flowers for Algernon Daniel Keyes", "The Dark Is Rising Susan Cooper", "Resurrection Leo Tolstoy", "Fearless Fourteen Janet Evanovich", "A Sentimental Journey Through France and Italy Laurence Sterne", "The King of Torts John Grisham", "The Graveyard Book Neil Gaiman", "The Quiet American Graham Greene", "The Chamber John Grisham", "The English Patient Michael Ondaatje", "Snow Falling on Cedars David Guterson", "The Long Winter Laura Ingalls Wilder", "Sarah, Plain and Tall Patricia MacLachlan", "Cross Country James Patterson", "The Spy Who Came In from the Cold John Le Carré", "A Game of Thrones George R.R Martin", "The Thorn Birds Colleen McCullough", "Old Yeller Fred Gipson", "Ramona Quimby, Age  Beverly Cleary", "Death In Venice Thomas Mann", "By the Shores of Silver Lake Laura Ingalls Wilder", "Inferno Dan Brown", "Schindler's List Thomas Keneally", "Jonathan Livingston Seagull Richard Bach", "The Stand Stephen King", "The Last Juror John Grisham", "Shiloh Phyllis Reynolds Naylor", "Girl With a Pearl Earring Tracy Chevalier", "The Murder of Roger Ackroyd Agatha Christie", "It Stephen King", "The Rainmaker John Grisham", "The Poisonwood Bible Barbara Kingsolver", "The Indian in the Cupboard Lynne Reid Banks", "The Maltese Falcon Dashiell Hammett", "The Warden Anthony Trollope", "The Summons John Grisham", "Encyclopedia Brown: Boy Detective Donald J Sobol", "The Time Traveler's Wife Audrey Niffenegger", "The Incredible Journey Sheila Burnford", "Daughter of Fortune Isabel Allende", "Shirley Charlotte Brontë", "Bud, Not Buddy Christopher Paul Curtis", "The Horse Whisperer Nicholas Evans", "The Street Lawyer John Grisham", "Nausea Jean-Paul Sartre", "To Have and Have Not Ernest Hemingway", "The Bridges of Madison County Robert James Waller", "Anne of the Island L.M Montgomery", "The Winter of Our Discontent John Steinbeck", "The Shining Stephen King", "The Tenant of Wildfell Hall Anne Brontë", "First Family David Baldacci", "The Partner John Grisham", "The Girl on the Train Paula Hawkins", "The Black Arrow: A Tale of the Two Roses Robert Louis Stevenson", "The Rise of Silas Lapham William Dea Howells", "The Choice Nicholas Sparks", "The Virginian: A Horseman of the Plains Owen Wister", "A Walk to Remember Nicholas Sparks", "The Maze Runner James Dashner", "The Westing Game Ellen Raskin", "Misty of Chincoteague Marguerite Henry", "Diary of a Wimpy Kid: The Last Straw Jeff Kinney", "King Solomon's Mines H Rider Haggard", "The Princess of Cleves Madame de (Marie-Madeleine Pioche de La Vergne) La Fayett", "Jacob Have I Loved Katherine Paterson", "Mrs Frisby and the Rats of NIMH Robert C O'Brien", "Misery Stephen King", "The Cider House Rules John Irving", "King of the Wind Marguerite Henry", "The Once and Future King T H White", "The Witches Roald Dahl", "The Subtle Knife Philip Pullman", "When You Reach Me Rebecca Stead", "Carrie Stephen King", "The Moon and Sixpence W Somerset Maugham", "The Higher Power of Lucky Susan Patron", "Looking Backward, - Edward Bellamy", "The Wings of the Dove Henry James", "The Summer of the Swans Betsy Cromer Byars", "Dangerous Liaisons Choderlos de Laclos", "Jurassic Park Michael Crichton", "The Absolutely True Diary of a Part-time Indian Sherman Alexie", "The Grey King Susan Cooper", "The Leopard Giuseppe Tomasi di Lampedusa", "The Mammoth Hunters Jean M Auel", "The Trumpet of the Swan E.B White", "The Lucky One Nicholas Sparks", "These Happy Golden Years Laura Ingalls Wilder", "Arrowsmith Sinclair Lewis", "Julie of the Wolves Jean Craighead George", "The Screwtape Letters C.S Lewis", "The Fall Albert Camus", "The No  Ladies' Detective Agency Alexander McCall Smith", "Worst Case James Patterson", "Lost Horizon James Hilton", "The Gunslinger Stephen King", "The Slave Dancer Paula Fox", "Harry Potter and the Half-Blood Prince J.K Rowling", "Inkheart Cornelia Funke", "Ramona and her Father Beverly Cleary", "Inkspell Cornelia Funke", "Ramona the Pest Beverly Cleary", "Walk Two Moons Sharon Creech", "Miss Peregrine's Home for Peculiar Children Ransom Riggs", "The Chocolate War Robert Cormier", "Sophie's Choice William Styron", "Looking for Alaska John Green", "Breakfast at Tiffany's Truman Capote", "The Razor's Edge W Somerset Maugham", "Dreamcatcher Stephen King", "Orlando Virginia Woolf", "The Things they Carried Tim O'Brien", "Little Town on the Prairie Laura Ingalls Wilder", "Nights in Rodanthe Nicholas Sparks", "The Amber Spyglass Philip Pullman", "The Miraculous Journey of Edward Tulane Kate DiCamillo", "Flatland Edwin A Abbott", "Diary of a Wimpy Kid Jeff Kinney", "The Memory Keeper's Daughter Kim Edwards", "The Wedding Nicholas Sparks", "Fried Green Tomatoes at the Whistle-Stop Cafe Fannie Flagg", "The Cricket in Times Square George Selden", "The Phantom Tollbooth Norton Juster", "Rob Roy Walter Scott", "The Death of Ivan Ilych Leo Tolstoy", "Alex Cross's Trial James Patterson", "Kenilworth Walter Scott", "The Life and Opinions of Tristram Shandy Laurence Sterne", "The Remains of theDay Kazuo Ishiguro", "M.C Higgins, The Great Virginia Hamilton", "Call It Courage Armstrong Sperry", "Go Set a Watchman Harper Lee", "Bleachers John Grisham", "Elijah of Buxton Christopher Paul Curtis", "Swimsuit James Patterson", "Cat's Cradle Kurt Vonnegut", "The Caine Mutiny Herman Wouk", "The Heart of the Matter Graham Greene", "Harriet, the Spy Louise Fitzhugh", "Darkness at Noon Arthur Koestler", "A Prayer for Owen Meany John Irving", "The God of Small Things Arundhati Roy", "The Associate John Grisham", "The Shack William P Young", "The Naked and the Dead Norman Mailer", "The Sea of Monsters Rick Riordan", "Stranger in a Strange Land Robert A Heinlein", "Vision in White Nora Roberts", "The Whipping Boy Sid Fleischman", "Room Emma Donoghue", "Deception Point Dan Brown"};
    static String[] authorName = {"Anthony Trollope", " L. Frank Baum", " Donald J. Sobol", " Tracy Chevalier", " Harper Lee", " Charlotte Bronté", " Laura Ingalls Wilder", " Louisa May Alcott", " Colleen McCullough", " Herman Melville", " F. Scott Fitzgerald", " Oscar Wilde", " Antoine de Saint-Exupéry", " James Fenimore Cooper", " Lewis Carroll", " Mark Twain", " Frances Hodgson Burnett", " George Orwell", " Jonathan Swift", " Audrey Niffenegger", " Jack London", " Jane Austen", " Mary Shelley", " Harriet Beecher Stowe", " Agatha Christie", " Thomas Mann", " J.R.R. Tolkien", " Beverly Cleary", " Nathaniel Hawthorne", " Fred Gipson", " Gustave Flaubert", " Sheila Burnford", " Miguel de Cervantes", " Isabel Allende", " John Grisham", " John Bunyan", " Kenneth Grahame", " Barbara Kingsolver", " Victor Hugo", " Thomas Keneally", " Charles Dickens", " Stephen King", " Aldous Huxley", " Leo Tolstoy", " John Steinbeck", " Alexandre Dumas", " Fyodor Dostoyevsky", " Lynne Reid Banks", " Richard Bach", " Phyllis Reynolds Naylor", " Dashiell Hammett", " Robert Louis Stevenson", " Bram Stoker", " Dan Brown", " Emily Bronté", " George R.R. Martin", " Thomas Hardy"};

    private List<String> abbrOfBookList = new ArrayList<>();
    private List<String> abbrOfUserList = new ArrayList<>();
    private List<String> abbrOfAuthorList = new ArrayList<>();

    public StringBuilder getQueryText() {
        queryText = new StringBuilder("");
        createUserCreationQuery();
        createUserFollowUserQuery();

        createBookCreationQuery();
        createUserReadBookConnection();

        createAuthorCreationQuery();
        createAuthorWriteBookQuery();

        createReturnQuery();

        // to fix data :
//        createFixUserFollowedDataQuery();


//        for (int i = 0; i < 100; i++) {
//            System.out.println(getRandomDoublePoint());
//        }
        return queryText;
    }

    public StringBuilder getFixDataQueryText() {
        StringBuilder query = new StringBuilder("");
        query.append(
                "MATCH (u:User)<-[:Follow]-(f:User)\n" +
                        "WITH u, COUNT(f) AS totalFollowers\n" +
                        "SET u.totalFollowers = totalFollowers\n" +
                        "\n" +
                        "WITH u\n" +
                        "MATCH (u)-[:Follow]->(f:User)\n" +
                        "WITH u, COUNT(f) AS totalFollowed\n" +
                        "SET u.totalFollowed = totalFollowed\n" +
                        "\n" +
                        "WITH u\n" +
                        "MATCH (a:Author)-[:Write]->(b:Book)\n" +
                        "WITH u, a, COUNT(b) AS totalBook\n" +
                        "SET a.totalBook = totalBook\n" +
                        "\n" +
                        "RETURN u, a\n");
        return query;
    }

    private void createReturnQuery() {
        StringBuilder returnQuery = new StringBuilder("RETURN ");
        for (int i = 0; i < abbrOfUserList.size(); i++) {
            returnQuery.append(abbrOfUserList.get(i) + ",");
        }
        for (int i = 0; i < abbrOfBookList.size(); i++) {
            returnQuery.append(abbrOfBookList.get(i) + ",");
        }
        for (int i = 0; i < abbrOfAuthorList.size(); i++) {
            returnQuery.append(abbrOfAuthorList.get(i) + ",");
        }
        Utility.deleteComma(returnQuery, 1);
        System.out.println(returnQuery);
    }


    private void createUserCreationQuery() {
        //create users
        StringBuilder createUserText = new StringBuilder();
        for (int i = 0; i < userNumber; i++) {
            createUserText.append("CREATE (u" + i + ":User{name:\"" + Utility.getRandomGenderName() + "\",lastname:\"" + Utility.getRandomGenderName() + "\",username:\"" + "user" + i + "\",password:\"pass\",totalFollowers:" + Utility.getRandomFollowers() + ",totalFollowed:" + Utility.getRandomFollowers() + "})\n");
            String abbr = "u" + i;
            abbrOfUserList.add(abbr);
        }
//        System.out.println(createUserText);
        queryText.append(createUserText + "\n");
    }

    private void createUserFollowUserQuery() {
        StringBuilder createFollowingAction = new StringBuilder("CREATE ");
        for (int i = 0; i < userNumber; i++) {
            int userIndex = i;
            int totalFollowingPeople = Utility.getRandomInt(userNumber);
            List<Integer> followedPeople = new ArrayList<>();
            followedPeople.add(userIndex);
            for (int j = 0; j < totalFollowingPeople; j++) {
                int indexOfPeopleToFollow = Utility.getRandomInt(userNumber);
                indexOfPeopleToFollow = Utility.getProperItemIndex(abbrOfUserList.size(), followedPeople, indexOfPeopleToFollow);
                createFollowingAction.append("(u" + i + ")-[:Follow]->(u" + indexOfPeopleToFollow + "),\n");
                followedPeople.add(indexOfPeopleToFollow);
            }
        }
        Utility.deleteComma(createFollowingAction, 2);
//        System.out.println(createFollowingAction);
        queryText.append(createFollowingAction + "\n");
    }

    private void createBookCreationQuery() {
        StringBuilder bookCreationQuery = new StringBuilder();
        for (int i = 0; i < bookName.length; i++) {
            bookCreationQuery.append("CREATE (b" + i + ":Book{name:\"" + bookName[i] + "\",totalRead:" + Utility.getRandomTotalRead() + ",point:" + Utility.getRandomDoublePoint() + "})\n");
            String abbr = "b" + i;
            abbrOfBookList.add(abbr);
        }
//        System.out.println(bookCreationQuery);
        queryText.append(bookCreationQuery + "\n");
    }

    private void createUserReadBookConnection() {
        StringBuilder userReadBookQueryBuilder = new StringBuilder("CREATE ");
        for (int i = 0; i < abbrOfUserList.size(); i++) {
            List<Integer> readBookList = new ArrayList<>();
            int userTotalReadBook = Utility.getRandomInt(250);
            for (int j = 0; j < userTotalReadBook; j++) {
                int bookIndex = Utility.getRandomInt(abbrOfBookList.size());
                bookIndex = Utility.getProperItemIndex(abbrOfBookList.size(), readBookList, bookIndex);
                readBookList.add(bookIndex);
                userReadBookQueryBuilder.append("(" + abbrOfUserList.get(i) + ")-[:Read{rate:" + (Utility.getRandomInt(10) + 1) + "}]->(" + abbrOfBookList.get(Utility.getRandomInt(abbrOfBookList.size())) + "),\n");
            }
        }
        Utility.deleteComma(userReadBookQueryBuilder, 2);
//        System.out.println(userReadBookQueryBuilder);
        queryText.append(userReadBookQueryBuilder + "\n");
    }

    private void createAuthorCreationQuery() {
        StringBuilder authorCreationQuery = new StringBuilder();
        for (int i = 0; i < authorName.length; i++) {
            String abbr = "a" + i;
            abbrOfAuthorList.add(abbr);
            String[] nameArr = authorName[i].trim().split(" ");
            String lastname = nameArr[nameArr.length - 1];
            StringBuilder name = new StringBuilder();
            for (int j = 0; j < nameArr.length - 1; j++) {
                name.append(nameArr[j] + " ");
            }
            Utility.deleteComma(name, 1);
            authorCreationQuery.append("CREATE (a" + i + ":Author{name:\"" + name + "\",lastname:\"" + lastname + "\",totalBook:" + Utility.getRandomInt(50) + ",point:" + Utility.getRandomDoublePoint() + "})\n");
        }
//        System.out.println(authorCreationQuery);
        queryText.append(authorCreationQuery + "\n");
    }

    /*create (a2)-[:Write]->(n2)*/
    private void createAuthorWriteBookQuery() {
        StringBuilder writeBookQuery = new StringBuilder("CREATE ");
        for (int i = 0; i < abbrOfBookList.size(); i++) {
            writeBookQuery.append("(" + abbrOfBookList.get(i) + ")<-[:Write]-" + "(" + abbrOfAuthorList.get(Utility.getRandomInt(abbrOfAuthorList.size())) + "),\n");
        }
        Utility.deleteComma(writeBookQuery, 2);

//        System.out.println(writeBookQuery);
        queryText.append(writeBookQuery + "\n");
    }

//    private  void createFixUserFollowedDataQuery() {
//        StringBuilder fixUserFollowersDataQuery = new StringBuilder("");
//        for (int i = 0; i < userNumber; i++) {
//            fixUserFollowersDataQuery.append(
//                    "MATCH (u" + userNumber + ":User)-[:Follow]->(u:User)" +
//                            "WHERE u" + userNumber + ".username = \"user" + userNumber + "\"" +
//                            "WITH COUNT(u) AS totalFollowed, u0");
//        }

}
    /*
    MATCH (u0:User)-[:Follow]->(u:User)
    WHERE u0.username = "user0"
    WITH COUNT(u) AS totalFollowed, u0
    SET u0.totalFollowed = totalFollowed*/
    /*
    *
    MATCH (a:Author{name:"Anthony", lastname:"Trollope"})-[:Write]->(b:Book)
WITH a, COUNT(b) AS totalBooks
SET a.totalBook = totalBooks
RETURN a
* */

