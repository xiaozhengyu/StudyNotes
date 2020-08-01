# https://www.ietf.org/rfc/rfc2396.txt

---

## Abstract

<u>A Uniform Resource Identifier (URI) is a compact string of characters for identifying an abstract or physical resource.</u>  This document defines the generic syntax of URI, including both absolute and relative forms, and guidelines for their use; it revises and replaces the generic definitions in RFC 1738 and RFC 1808.

This document defines a grammar that is a superset of all valid URI, such that an implementation can parse the common components of a URI reference without knowing the scheme-specific requirements of every possible identifier type.  This document does not define a generative grammar for URI; that task will be performed by the individual specifications of each URI scheme.

> 统一资源标识符（URI）是紧凑的字符串，用于标识抽象资源或物理资源。

## 1. Introduction

<u>Uniform Resource Identifiers (URI) provide a simple and extensible means for identifying a resource.</u>  This specification of URI syntax and semantics is derived from concepts introduced by the World Wide Web global information initiative, whose use of such objects dates from 1990 and is described in "Universal Resource Identifiers in WWW" [RFC1630].  The specification of URI is designed to meet the recommendations laid out in "Functional Recommendations for Internet Resource Locators" [RFC1736] and "Functional Requirements for Uniform Resource Names" [RFC1737].

This document updates and merges "Uniform Resource Locators" [RFC1738] and "Relative Uniform Resource Locators" [RFC1808] in order to define a single, generic syntax for all URI.  It excludes those portions of RFC 1738 that defined the specific syntax of individual URL schemes; those portions will be updated as separate documents, as will the process for registration of new URI schemes.  This document does not discuss the issues and recommendation for aling with characters outside of the US-ASCII character set [ASCII]; those recommendations are discussed in a separate document.

All significant changes from the prior RFCs are noted in Appendix G.

> 统一资源标识符（URI）提供了一种简单且可扩展的方式来标识资源。

### 1.1 Overview of  URI

URI are characterized by the following definitions:

> **Uniform**
>
> Uniformity provides several benefits: it allows different types of resource identifiers to be used in the same context, even when the mechanisms used to access those resources may differ; it allows uniform semantic interpretation of common syntactic conventions across different types of resource identifiers; it allows introduction of new types of resource identifiers without interfering with the way that existing identifiers are used; and, it allows the identifiers to be reused in many different contexts, thus permitting new applications or protocols to leverage a pre-existing, large, and widely-used set of resource identifiers.

> **Resource**
>
> <u>A resource can be anything that has identity.</u>  Familiar examples include an electronic document, an image, a service (e.g., "today's weather report for Los Angeles"), and a collection of other resources.  Not all resources are network "retrievable"; e.g., human beings, corporations, and bound books in a library can also be considered resources.
>
> <u>The resource is the conceptual mapping to an entity or set of entities, not necessarily the entity which corresponds to that mapping at any particular instance in time.</u> Thus, a resource can remain constant even when its content---the entities to which it currently corresponds---changes over time, provided that the onceptual mapping is not changed in the process.
>
> > “资源”可以是任何具有身份的东西。
> >
> > 资源是到一个实体或一组实体的概念性映射，不一定是在任何特定时间与该映射相对应的实体。

> **Identifier**
>
> <u>An identifier is an object that can act as a reference to something that has identity.  In the case of URI, the object is a sequence of characters with a restricted synta.</u>
>
> > “标识符”是一个可以代表资源引用的对象，在URI中，标识符是一个满足一定语法限制的字符串。

<u>Having identified a resource, a system may perform a variety of operations on the resource, as might be characterized by such words as `access', `update', `replace', or `find attributes'.</u>

> 系统可以通过标识符对该资源执行各种操作，比如所谓的“访问”、“更新”、“替换”、“查找属性”。

### 1.2 URI, URL, and URN

<u>A URI can be further classified as a locator, a name, or both.  The term "Uniform Resource Locator" (URL) refers to the subset of URI that identify resources via a representation of their primary access mechanism (e.g., their network "location"), rather than identifying the resource by name or by some other attribute(s) of that resource. The term "Uniform Resource Name" (URN) refers to the subset of URI that are required to remain globally unique and persistent even when the resource ceases to exist or becomes unavailable.</u>

> “资源标识符”可以进一步划分为“资源定位符”、“资源名称”或二者的结合体。URL（统一资源定位符）是URI的子集，它主要通过“访问机制”（例如“网络位置”）来标识资源，而不是通过名称或者其他属性来标识资源。URN（统一资源名称）也是URL的子集，即使资源不再存在或变得不可使用，统一资源名称也必须保证全局唯一性和持久性。
>
> > 个人理解：“160716XXXX”这个学号，相当于我这个资源的“统一资源名称”；“1525997XXXX”这个手机号，相当于我这个资源的“统一资源定位符”。学号可以唯一的标识我这个“资源”，即使哪天我毕业了这个学号会为我而保留，但通过学号无法直接“访问”我这个“资源”；手机号在我持有它的这段时间内可以唯一的标识我这个“资源”，通过手机号可以“访问”我这个“资源”，但是如果哪天我弃用了这个手机号，这个“资源定位符”就失效了。

The URI scheme (Section 3.1) defines the namespace of the URI, and thus may further restrict the syntax and semantics of identifiers using that scheme.  This specification defines those elements of the URI syntax that are either required of all URI schemes or are common to many URI schemes.  It thus defines the syntax and semantics that are needed to implement a scheme-independent parsing mechanism for URI references, such that the scheme-dependent handling of a URI can be postponed until the scheme-dependent semantics are needed.  We use the term URL below when describing syntax or semantics that only apply to locators.

Although many URL schemes are named after protocols, this does not imply that the only way to access the URL's resource is via the named protocol.  Gateways, proxies, caches, and name resolution services might be used to access some resources, independent of the protocol of their origin, and the resolution of some URL may require the use of more than one protocol (e.g., both DNS and HTTP are typically used to access an "http" URL's resource when it can't be found in a local cache).

<u>A URN differs from a URL in that it's primary purpose is persistent labeling of a resource with an identifier.</u>  That identifier is drawn from one of a set of defined namespaces, each of which has its own set name structure and assignment procedures.  The "urn" scheme has been reserved to establish the requirements for a standardized URN namespace, as defined in "URN Syntax" [RFC2141] and its related specifications.

> URN 与 URL 的不同之处在于，URN的主要目标是使用标识符对资源进行持久标记。

Most of the examples in this specification demonstrate URL, since they allow the most varied use of the syntax and often have a hierarchical namespace.  A parser of the URI syntax is capable of parsing both URL and URN references as a generic URI; once the scheme is determined, the scheme-specific parsing can be performed on the generic URI components.  In other words, the URI syntax is a superset of the syntax of all URI schemes.