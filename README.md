# SOLID design principles
SOLID is a set of related design principles that help with writing
readable, maintainable and testable code.

Many introductions to SOLID are overly academic; this one aims to be more
relatable, taking a simple ecommerce application through a succession of
refactors to produce a cleaner design, gradually introducing the SOLID
principles along the way.

## Presentation
Although a self-guided path through this demo is possible, it was
originally intended to be delivered in a 45 minute to one hour "learning
session".

For that purpose there is an [associated slide deck](solid.pptx).

## SOLID
The principles introduced during this tutorial are:

* **S**ingle Reponsibility Principle
* **O**pen/Closed Principle
* **L**iskov Substitution Principle
* **I**nterface Segregation Principle
* **D**ependency Inversion Principle

These will not appear in that order, so when following the self-guided
path it is advisable to keep this list at hand and use it as a "bingo
card".

## Problem statement
To show these principles in action, an application will be developed and
reworked over the course of the demo. This application should
address the following problem statement:

* Build a shopping app for the Two Feet Ahead store
* Two Feet Ahead sell hats and shoes, and only hats and shoes

and satisfy these user stories:

* As a user, I want to be greeted cordially at the store entrance
* As a user, I want to be shown to a department (hats or shoes)
* As a user, I want to be able to add items to my bag
* As a user, I want to be able to pay for the items in my bag

Successive versions of the code (and associated tests) can be found in the
`src` subdirectory. Comments in the `Main.java` outline what has changed
from the previous version, and why.
