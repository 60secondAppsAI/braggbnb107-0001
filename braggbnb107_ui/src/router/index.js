import Vue from "vue";
import VueRouter from "vue-router";
import DefaultLayout from "@/layouts/DefaultLayout.vue";
import Propertys from  '@/pages/Propertys.vue';
import PropertyDetail from  '@/pages/PropertyDetail.vue';
import Users from  '@/pages/Users.vue';
import UserDetail from  '@/pages/UserDetail.vue';
import Bookings from  '@/pages/Bookings.vue';
import BookingDetail from  '@/pages/BookingDetail.vue';
import Guests from  '@/pages/Guests.vue';
import GuestDetail from  '@/pages/GuestDetail.vue';
import Reviews from  '@/pages/Reviews.vue';
import ReviewDetail from  '@/pages/ReviewDetail.vue';
import Listings from  '@/pages/Listings.vue';
import ListingDetail from  '@/pages/ListingDetail.vue';
import Images from  '@/pages/Images.vue';
import ImageDetail from  '@/pages/ImageDetail.vue';
import Payments from  '@/pages/Payments.vue';
import PaymentDetail from  '@/pages/PaymentDetail.vue';
import Amenitys from  '@/pages/Amenitys.vue';
import AmenityDetail from  '@/pages/AmenityDetail.vue';
import HostResponses from  '@/pages/HostResponses.vue';
import HostResponseDetail from  '@/pages/HostResponseDetail.vue';
import Messages from  '@/pages/Messages.vue';
import MessageDetail from  '@/pages/MessageDetail.vue';
import Transactions from  '@/pages/Transactions.vue';
import TransactionDetail from  '@/pages/TransactionDetail.vue';
import CancellationPolicys from  '@/pages/CancellationPolicys.vue';
import CancellationPolicyDetail from  '@/pages/CancellationPolicyDetail.vue';
import Reports from  '@/pages/Reports.vue';
import ReportDetail from  '@/pages/ReportDetail.vue';
import Wishlists from  '@/pages/Wishlists.vue';
import WishlistDetail from  '@/pages/WishlistDetail.vue';
import Events from  '@/pages/Events.vue';
import EventDetail from  '@/pages/EventDetail.vue';
import Notifications from  '@/pages/Notifications.vue';
import NotificationDetail from  '@/pages/NotificationDetail.vue';
import Addresss from  '@/pages/Addresss.vue';
import AddressDetail from  '@/pages/AddressDetail.vue';
import IdentityVerifications from  '@/pages/IdentityVerifications.vue';
import IdentityVerificationDetail from  '@/pages/IdentityVerificationDetail.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: () => import("../views/HomeView.vue"),
			redirect: '/propertys',
																			  },
  {
    path: "/pricing",
    name: "PricingView",
    component: () => import("../views/PricingView.vue"),
  },
  {
    path: "/arts-gallery",
    name: "ArtsGalleryView",
    component: () => import("../views/ArtsGalleryView.vue"),
  },
  {
    path: "/checkout/:id",
    name: "CheckoutView",
    component: () => import("../views/CheckoutView.vue"),
  },
  {
    path: "/stripe-checkout",
    name: "StripeCheckoutView",
    component: () => import("../views/StripeCheckoutView.vue"),
  },
	{
		path: '/propertys',
		name: 'Propertys',
		layout: DefaultLayout,
		component: Propertys,
	},
	{
	    path: '/property/:propertyId', 
	    name: 'PropertyDetail',
		layout: DefaultLayout,
	    component: PropertyDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/users',
		name: 'Users',
		layout: DefaultLayout,
		component: Users,
	},
	{
	    path: '/user/:userId', 
	    name: 'UserDetail',
		layout: DefaultLayout,
	    component: UserDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/bookings',
		name: 'Bookings',
		layout: DefaultLayout,
		component: Bookings,
	},
	{
	    path: '/booking/:bookingId', 
	    name: 'BookingDetail',
		layout: DefaultLayout,
	    component: BookingDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/guests',
		name: 'Guests',
		layout: DefaultLayout,
		component: Guests,
	},
	{
	    path: '/guest/:guestId', 
	    name: 'GuestDetail',
		layout: DefaultLayout,
	    component: GuestDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/reviews',
		name: 'Reviews',
		layout: DefaultLayout,
		component: Reviews,
	},
	{
	    path: '/review/:reviewId', 
	    name: 'ReviewDetail',
		layout: DefaultLayout,
	    component: ReviewDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/listings',
		name: 'Listings',
		layout: DefaultLayout,
		component: Listings,
	},
	{
	    path: '/listing/:listingId', 
	    name: 'ListingDetail',
		layout: DefaultLayout,
	    component: ListingDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/images',
		name: 'Images',
		layout: DefaultLayout,
		component: Images,
	},
	{
	    path: '/image/:imageId', 
	    name: 'ImageDetail',
		layout: DefaultLayout,
	    component: ImageDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/payments',
		name: 'Payments',
		layout: DefaultLayout,
		component: Payments,
	},
	{
	    path: '/payment/:paymentId', 
	    name: 'PaymentDetail',
		layout: DefaultLayout,
	    component: PaymentDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/amenitys',
		name: 'Amenitys',
		layout: DefaultLayout,
		component: Amenitys,
	},
	{
	    path: '/amenity/:amenityId', 
	    name: 'AmenityDetail',
		layout: DefaultLayout,
	    component: AmenityDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/hostResponses',
		name: 'HostResponses',
		layout: DefaultLayout,
		component: HostResponses,
	},
	{
	    path: '/hostResponse/:hostResponseId', 
	    name: 'HostResponseDetail',
		layout: DefaultLayout,
	    component: HostResponseDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/messages',
		name: 'Messages',
		layout: DefaultLayout,
		component: Messages,
	},
	{
	    path: '/message/:messageId', 
	    name: 'MessageDetail',
		layout: DefaultLayout,
	    component: MessageDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/transactions',
		name: 'Transactions',
		layout: DefaultLayout,
		component: Transactions,
	},
	{
	    path: '/transaction/:transactionId', 
	    name: 'TransactionDetail',
		layout: DefaultLayout,
	    component: TransactionDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/cancellationPolicys',
		name: 'CancellationPolicys',
		layout: DefaultLayout,
		component: CancellationPolicys,
	},
	{
	    path: '/cancellationPolicy/:cancellationPolicyId', 
	    name: 'CancellationPolicyDetail',
		layout: DefaultLayout,
	    component: CancellationPolicyDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/reports',
		name: 'Reports',
		layout: DefaultLayout,
		component: Reports,
	},
	{
	    path: '/report/:reportId', 
	    name: 'ReportDetail',
		layout: DefaultLayout,
	    component: ReportDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/wishlists',
		name: 'Wishlists',
		layout: DefaultLayout,
		component: Wishlists,
	},
	{
	    path: '/wishlist/:wishlistId', 
	    name: 'WishlistDetail',
		layout: DefaultLayout,
	    component: WishlistDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/events',
		name: 'Events',
		layout: DefaultLayout,
		component: Events,
	},
	{
	    path: '/event/:eventId', 
	    name: 'EventDetail',
		layout: DefaultLayout,
	    component: EventDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/notifications',
		name: 'Notifications',
		layout: DefaultLayout,
		component: Notifications,
	},
	{
	    path: '/notification/:notificationId', 
	    name: 'NotificationDetail',
		layout: DefaultLayout,
	    component: NotificationDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/addresss',
		name: 'Addresss',
		layout: DefaultLayout,
		component: Addresss,
	},
	{
	    path: '/address/:addressId', 
	    name: 'AddressDetail',
		layout: DefaultLayout,
	    component: AddressDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/identityVerifications',
		name: 'IdentityVerifications',
		layout: DefaultLayout,
		component: IdentityVerifications,
	},
	{
	    path: '/identityVerification/:identityVerificationId', 
	    name: 'IdentityVerificationDetail',
		layout: DefaultLayout,
	    component: IdentityVerificationDetail,
	    props: true // Pass route params as props to the component
  	},
];

const router = new VueRouter({
  mode: "hash",
  base: process.env.BASE_URL,
  routes,
});

export default router;
